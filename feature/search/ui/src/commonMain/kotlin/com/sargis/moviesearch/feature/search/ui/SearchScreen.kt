package com.sargis.moviesearch.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookpedia.feature.search.ui.generated.resources.Res
import bookpedia.feature.search.ui.generated.resources.favorites
import bookpedia.feature.search.ui.generated.resources.no_favorite_movies
import bookpedia.feature.search.ui.generated.resources.no_search_results
import bookpedia.feature.search.ui.generated.resources.search_results
import com.sargis.moviesearch.core.ui.DarkBlue
import com.sargis.moviesearch.core.ui.DesertWhite
import com.sargis.moviesearch.core.ui.SandYellow
import com.sargis.moviesearch.feature.search.ui.components.MovieList
import com.sargis.moviesearch.feature.search.ui.components.MovieSearchBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onMovieClick: (String) -> Unit
) {

    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()

    SearchScreenContent(
        modifier = modifier,
        query = query,
        onAction = { action ->
            when (action) {
                is MoviesAction.OnMovieClick -> onMovieClick(action.movie.id)
                else -> viewModel.onAction(action)
            }
        },
        uiState = uiState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    query: String,
    onAction: (MoviesAction) -> Unit,
    uiState: SearchUiState
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState { 2 }
    val searchResultsListState = rememberLazyListState()
    val favoriteMovieListState = rememberLazyListState()

    LaunchedEffect(uiState.searchedMovies) {
        searchResultsListState.animateScrollToItem(0)
    }

    LaunchedEffect(uiState.selectedTabIndex) {
        pagerState.animateScrollToPage(uiState.selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        onAction(MoviesAction.OnTabSelected(pagerState.currentPage))
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MovieSearchBar(
                searchQuery = query,
                onSearchQueryChange = {
                    onAction(MoviesAction.OnSearchQueryChange(it))
                },
                onImeSearch = {
                    keyboardController?.hide()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBlue)
                    .statusBarsPadding()
                    .widthIn(400.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .background(DarkBlue)
                .fillMaxSize()
                .padding(innerPadding),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = uiState.selectedTabIndex,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    containerColor = DesertWhite,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTabIndex])
                        )
                    }
                ) {
                    Tab(
                        selected = uiState.selectedTabIndex == 0,
                        onClick = {
                            onAction(MoviesAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                    Tab(
                        selected = uiState.selectedTabIndex == 1,
                        onClick = {
                            onAction(MoviesAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> {
                                if (uiState.isLoading) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                } else {
                                    when {
                                        uiState.error != null -> {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(text = uiState.error.asString())
                                            }
                                        }

                                        uiState.searchedMovies.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.no_search_results),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        else -> {
                                            MovieList(
                                                modifier = Modifier.fillMaxSize(),
                                                movies = uiState.searchedMovies,
                                                onClick = {
                                                    onAction(MoviesAction.OnMovieClick(it))
                                                },
                                                // scrollState = searchResultsListState
                                            )
                                        }
                                    }
                                }
                            }
                            1 -> {
                                if (uiState.favoriteMovies.isEmpty()) {
                                    Text(
                                        text = stringResource(Res.string.no_favorite_movies),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                    )
                                } else {
                                    MovieList(
                                        modifier = Modifier.fillMaxSize(),
                                        movies = uiState.favoriteMovies,
                                        onClick = {
                                            onAction(MoviesAction.OnMovieClick(it))
                                        },
                                        // scrollState = favoriteMovieListState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenContentPreview() {
    SearchScreenContent(
        query = "Harry Potter",
        uiState = SearchUiState(),
        onAction = {},
    )
}