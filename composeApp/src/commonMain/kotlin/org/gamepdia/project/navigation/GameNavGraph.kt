package org.gamepdia.project.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.gamepdia.ui.game.GameScreen
import org.gamepdia.ui.gameDetails.GameDetailsScreen

object GameNavGraph : BaseNavGraph {


    sealed class Dest(val route: String) {
        data object Root : Dest("/game-root")
        data object Game : Dest("/game")
        data object Details : Dest("/game_details/{id}") {
            fun getRoute(id: Int) = "/game_details/$id"
        }
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            route = Dest.Root.route,
            startDestination = Dest.Game.route
        ) {
            composable(Dest.Game.route) {
                GameScreen(
                    modifier = modifier.fillMaxSize(),
                    onSearchClick = {
                        navHostController.navigate(
                            SearchNavGraph.Dest.Search.route
                        )
                    },
                    onFavoriteClick = {
                        navHostController.navigate(route = FavoriteNavGraph.Dest.Root.route)
                    },
                    onCardClick = { id ->
                        navHostController.navigate(
                            Dest.Details.getRoute(id)
                        )
                    }
                )
            }



            composable(Dest.Details.route){
                val id = it.arguments?.getString("id") ?: ""
                GameDetailsScreen(id){
                    navHostController.popBackStack()
                }
            }


        }
    }
}