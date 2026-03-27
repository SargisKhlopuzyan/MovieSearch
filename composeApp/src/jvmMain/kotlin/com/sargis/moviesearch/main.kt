package com.sargis.moviesearch

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sargis.moviesearch.shared.di.initKoin

fun main() = application {

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "MovieSearch",
    ) {
        App()
    }
}