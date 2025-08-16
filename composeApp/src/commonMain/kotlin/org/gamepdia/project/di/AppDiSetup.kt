package org.gamepdia.project.di

import org.gamepdia.coreDatabase.di.databaseModule
import org.gamepdia.coreNetwork.di.getCoreNetworkModule
import org.gamepdia.data.di.getGameDataModule
import org.gamepdia.di.favoriteModule
import org.gamepdia.di.getFavoriteDomainModule
import org.gamepdia.di.getSearchDataModule
import org.gamepdia.di.getSearchDomainModule
import org.gamepdia.domain.di.getGameDomainModule
import org.gamepdia.favorite.di.favoriteViewModelModule
import org.gamepdia.search.di.getSearchUiModule
import org.gamepdia.ui.di.getGameUiModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(
    koinApplication: ((KoinApplication) -> Unit ) ? = null
){
    startKoin{
        modules(
            getCoreNetworkModule(),
            getGameDataModule(),
            getGameDomainModule(),
            getGameUiModule(),
            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),
            databaseModule(),
            favoriteModule(),
            getFavoriteDomainModule(),
            favoriteViewModelModule()
        )
    }
}