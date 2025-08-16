package org.gamepdia.favorite.di

import org.gamepdia.favorite.ui.FavoriteViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel



fun favoriteViewModelModule() = module {
    viewModel {
        FavoriteViewModel(
            getFavoriteGamesUseCase = get(),
            deleteGameUseCase = get()
        )
    }
}