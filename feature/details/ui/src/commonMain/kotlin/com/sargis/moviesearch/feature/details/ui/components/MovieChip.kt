package com.sargis.moviesearch.feature.details.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sargis.moviesearch.core.ui.LightBlue

enum class ChipSize {
    SMALL, REGULAR
}

@Composable
fun MovieChip(
    modifier: Modifier,
    size: ChipSize = ChipSize.REGULAR,
    chipContent: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .widthIn(
                min = when (size) {
                    ChipSize.SMALL -> 50.dp
                    ChipSize.REGULAR -> 80.dp
                }
            )
            .clip(RoundedCornerShape(16.dp))
            .background(LightBlue)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        chipContent()
    }
}

@Preview(showBackground = true)
@Composable
fun MovieChipPreview(
) {
    MovieChip(
        modifier = Modifier,
        size = ChipSize.REGULAR,
        chipContent = @Composable {
            Text(
                text = "** ChipContent **",
                style = MaterialTheme.typography.labelSmall
            )
        }
    )
}