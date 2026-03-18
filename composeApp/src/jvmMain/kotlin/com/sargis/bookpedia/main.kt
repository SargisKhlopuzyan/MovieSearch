package com.sargis.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sargis.shared.di.initKoin

fun main() = application {

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Bookpedia",
    ) {
        App()
    }
}