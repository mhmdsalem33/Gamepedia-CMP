package org.gamepdia.project.di

import org.gamepdia.coreNetwork.di.getCoreNetworkModule
import org.gamepdia.data.di.getGameDataModule
import org.gamepdia.di.getSearchDataModule
import org.gamepdia.di.getSearchDomainModule
import org.gamepdia.domain.di.getGameDomainModule
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
            getSearchUiModule()
        )
    }
}