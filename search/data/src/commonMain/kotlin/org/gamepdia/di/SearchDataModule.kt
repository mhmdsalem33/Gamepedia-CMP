package org.gamepdia.di

import org.gamepdia.repository.SearchRepository
import org.gamepdia.repository.SearchRepositoryImpl
import org.koin.dsl.module

fun getSearchDataModule() = module {
    factory <SearchRepository> { SearchRepositoryImpl(apiService = get() )}
}