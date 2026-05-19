package com.sargis.moviesearch.feature.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sargis.moviesearch.core.ui.SandYellow
import com.sargis.moviesearch.feature.details.ui.components.BlurredImageBackground
import com.sargis.moviesearch.feature.details.ui.components.MovieChip
import com.sargis.moviesearch.feature.details.ui.components.TitledContent
import moviesearch.designsystem.generated.resources.Res
import moviesearch.designsystem.generated.resources.origin_country
import moviesearch.designsystem.generated.resources.original_language
import moviesearch.designsystem.generated.resources.rating
import moviesearch.designsystem.generated.resources.release_date
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    id: String,
    onBackClick: () -> Unit
) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.onAction(MovieDetailsAction.OnSelectedMovieChanged(id))
    }

    DetailsScreenContent(
        modifier = modifier,
        uiState = uiState,
        onAction = { action ->
            when (action) {
                MovieDetailsAction.OnBackClick -> onBackClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: DetailsUiState,
    onAction: (MovieDetailsAction) -> Unit
) {
    BlurredImageBackground(
        modifier = modifier,
        imageUrl = uiState.movieDetails?.imageUrl,
        isFavorite = uiState.isFavorite,
        onFavoriteClick = {
            onAction(MovieDetailsAction.OnFavoriteClick)
        },
        onBackClick = {
            onAction(MovieDetailsAction.OnBackClick)
        }
    ) {
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(uiState.error)
                }
            }

            uiState.movieDetails != null -> {

                val movieDetails = uiState.movieDetails

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        //.padding(innerPadding)
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = movieDetails.title,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Start
                    )

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            alignment = Alignment.CenterHorizontally
                        )
                    ) {
                        movieDetails.releaseDate?.let { releaseDate ->
                            TitledContent(
                                title = stringResource(Res.string.release_date)
                            ) {
                                MovieChip {
                                    Text(text = releaseDate)
                                }
                            }
                        }

                        if (movieDetails.originalLanguage.isNotBlank()) {
                            TitledContent(
                                title = stringResource(Res.string.original_language)
                            ) {
                                MovieChip {
                                    Text(text = movieDetails.originalLanguage)
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            alignment = Alignment.CenterHorizontally
                        )
                    ) {
                        val averageVote = movieDetails.averageVote
                        val voteCount = movieDetails.voteCount
                        if (averageVote != null && voteCount != null && voteCount > 0) {
                            TitledContent(
                                title = stringResource(Res.string.rating)
                            ) {
                                MovieChip {
                                    Text(text = "$averageVote")
                                    Spacer(Modifier.width(4.dp))
                                    Icon(
                                        modifier = Modifier.size(18.dp),
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = SandYellow
                                    )
                                }
                            }
                        }

                        if (movieDetails.originCountry.isNotEmpty()) {
                            TitledContent(
                                title = stringResource(Res.string.origin_country)
                            ) {
                                MovieChip {
                                    Text(text = movieDetails.originCountry.joinToString())
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = movieDetails.overview,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start
                    )

                    Spacer(Modifier.height(30.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenContentPreview() {
    DetailsScreenContent(
        uiState = DetailsUiState(),
    ) { }
}