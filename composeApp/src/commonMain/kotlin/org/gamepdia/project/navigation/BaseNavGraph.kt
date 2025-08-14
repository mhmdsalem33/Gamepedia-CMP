package org.gamepdia.project.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface BaseNavGraph {
    fun build(
        modifier: Modifier = Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
        )
}