package com.sargis.moviesearch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bookpedia.feature.search.ui.generated.resources.Res
import bookpedia.feature.search.ui.generated.resources.movie_error
import coil3.compose.SubcomposeAsyncImage
import com.sargis.moviesearch.domain.mock.mockMovie
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieItemImage(
    imageUrl: String,
    onClick: () -> Unit
) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .height(150.dp)
            .aspectRatio(
                ratio = 0.65f,
                matchHeightConstraintsFirst = true
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(12.dp)),
        model = imageUrl,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.movie_error),
                    contentDescription = null
                )
            }
        },
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun MovieItemImagePreview() {
    MovieItemImage(
        imageUrl = mockMovie.imageUrl,
        onClick = {}
    )
}