package com.sargis.moviesearch.feature.details.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TitledContent(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun TitledContentPreview(
) {
    TitledContent(
        title = "Rating",
        modifier = Modifier,
        content = @Composable {
            Text(
                text = "** Content **",
                style = MaterialTheme.typography.labelSmall
            )
        }
    )
}