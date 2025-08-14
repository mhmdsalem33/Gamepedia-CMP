package org.gamepdia.ui.di

import org.gamepdia.ui.game.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


fun getGameUiModule() = module {
    viewModel { GameViewModel(getGamesUseCase = get()) }
}