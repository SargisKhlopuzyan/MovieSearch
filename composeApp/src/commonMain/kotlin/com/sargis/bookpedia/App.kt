package com.sargis.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sargis.bookpedia.navigation.BaseNavGraph
import com.sargis.bookpedia.navigation.SearchNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize(),
        ) { innerPadding ->
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = SearchNavGraph.Dest.Root
            ) {
                listOf<BaseNavGraph>(
                    SearchNavGraph
                ).forEach {
                    it.build(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        navController = navController,
                        navGraphBuilder = this
                    )
                }
            }
        }
    }
}