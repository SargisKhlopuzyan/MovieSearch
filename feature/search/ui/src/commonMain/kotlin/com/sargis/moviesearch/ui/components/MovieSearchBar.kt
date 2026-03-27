package com.sargis.moviesearch.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import bookpedia.feature.search.ui.generated.resources.Res
import bookpedia.feature.search.ui.generated.resources.search_hint
import com.sargis.moviesearch.coreui.DarkBlue
import com.sargis.moviesearch.coreui.DesertWhite
import com.sargis.moviesearch.coreui.SandYellow
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = SandYellow,
            backgroundColor = SandYellow
        )
    ) {
        OutlinedTextField(
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(100),
                    color = DesertWhite
                )
                .minimumInteractiveComponentSize(),
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(100),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = DarkBlue,
                focusedBorderColor = SandYellow
            ),
            placeholder = {
                Text(text = stringResource(Res.string.search_hint))
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        onImeSearch()
                    },
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotEmpty(),
                ) {
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            onSearchQueryChange("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        )
                    }
                }
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    onImeSearch()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieSearchBarPreview() {
    MaterialTheme {
        MovieSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}