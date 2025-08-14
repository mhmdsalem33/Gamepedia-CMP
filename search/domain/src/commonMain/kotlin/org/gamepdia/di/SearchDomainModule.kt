package org.gamepdia.di

import org.gamepdia.usecases.GetSearchGamesUseCase
import org.koin.dsl.module

fun getSearchDomainModule() = module {
    factory {  GetSearchGamesUseCase( searchRepository = get() )  }
}