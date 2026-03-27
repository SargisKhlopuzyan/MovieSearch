package com.sargis.moviesearch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sargis.moviesearch.domain.mock.mockMovies
import com.sargis.moviesearch.domain.model.Movie

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = movies.size,
            key = { movies[it].id },
            contentType = { movies[it].id }) { index ->
            MovieListItem(
                modifier = Modifier
                    .widthIn(700.dp)
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                    ).padding(
                        top = if (index == 0) 12.dp else 0.dp
                    ).padding(
                        bottom = if (index == movies.size - 1) 12.dp else 0.dp
                    ),
                movie = movies[index],
                onClick = {
                    onClick(movies[index])
                }
            )
        }
    }
}

@Preview
@Composable
fun MovieListPreview() {
    MovieList(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        movies = mockMovies,
        onClick = {}
    )
}