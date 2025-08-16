package org.gamepdia.project


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.gamepdia.project.navigation.FavoriteNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.gamepdia.project.navigation.GameNavGraph
import org.gamepdia.project.navigation.SearchNavGraph

@Composable
@Preview
fun App() {

    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = GameNavGraph.Dest.Root.route) {

            listOf(
                GameNavGraph,
                SearchNavGraph,
                FavoriteNavGraph
            ).forEach {
                it.build(
                    modifier = Modifier.fillMaxSize(),
                    navHostController = navController,
                    navGraphBuilder = this
                )
            }
        }
    }
}