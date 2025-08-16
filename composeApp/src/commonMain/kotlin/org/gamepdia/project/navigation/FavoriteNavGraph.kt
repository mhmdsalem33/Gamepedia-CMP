package org.gamepdia.project.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.gamepdia.favorite.ui.FavoriteScreen

object FavoriteNavGraph : BaseNavGraph {

    sealed class Dest(val route: String){
        data object Root : Dest("/favorite_root")
        data object Favorite : Dest("/favorite")
    }


    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
       navGraphBuilder.navigation(
           route = Dest.Root.route,
           startDestination = Dest.Favorite.route
       ){
           composable( route = Dest.Favorite.route ){
               FavoriteScreen(
                   modifier = Modifier.fillMaxSize(),
                   onBackClick = {
                       navHostController.popBackStack()
                   },
                   onClick = { id ->
                       navHostController.navigate(GameNavGraph.Dest.Details.getRoute(id))
                   }
               )
           }
       }
    }

}