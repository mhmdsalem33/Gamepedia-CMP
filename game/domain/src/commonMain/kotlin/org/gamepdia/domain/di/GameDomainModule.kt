package org.gamepdia.domain.di

import org.gamepdia.domain.useCase.GetGamesUseCase
import org.koin.dsl.module


fun getGameDomainModule() = module { factory { GetGamesUseCase( gameRepository = get())  } }