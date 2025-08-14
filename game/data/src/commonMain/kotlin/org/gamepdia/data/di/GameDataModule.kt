package org.gamepdia.data.di

import org.gamepdia.data.repository.GameRepositoryImpl
import org.gamepdia.domain.repositroy.GameRepository
import org.koin.dsl.module

fun getGameDataModule() = module {
    factory<GameRepository> { GameRepositoryImpl(apiService = get()) }
}