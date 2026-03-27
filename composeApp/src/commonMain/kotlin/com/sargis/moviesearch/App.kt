package com.sargis.moviesearch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sargis.moviesearch.navigation.BaseNavGraph
import com.sargis.moviesearch.navigation.DetailsNavGraph
import com.sargis.moviesearch.navigation.SearchNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = SearchNavGraph.Dest.Root
        ) {
            listOf<BaseNavGraph>(
                SearchNavGraph,
                DetailsNavGraph,
            ).forEach { baseNavGraph ->
                baseNavGraph.build(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    navGraphBuilder = this
                )
            }
        }
    }
}