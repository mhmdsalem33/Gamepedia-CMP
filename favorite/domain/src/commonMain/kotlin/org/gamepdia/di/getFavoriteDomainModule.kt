package org.gamepdia.di

import org.gamepdia.usecase.DeleteGameUseCase
import org.gamepdia.usecase.GetFavoriteGamesUseCase
import org.gamepdia.usecase.UpsertGameUseCase
import org.koin.dsl.module

fun getFavoriteDomainModule() = module{
    factory{ DeleteGameUseCase(get()) }
    factory{ UpsertGameUseCase(get()) }
    factory{ GetFavoriteGamesUseCase(get()) }
}