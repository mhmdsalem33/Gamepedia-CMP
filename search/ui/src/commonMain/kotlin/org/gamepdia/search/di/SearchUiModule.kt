package org.gamepdia.search.di

import org.gamepdia.search.ui.SearchViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel


fun getSearchUiModule() = module {
    viewModel{ SearchViewModel(getSearchGamesUseCase = get()) }
}