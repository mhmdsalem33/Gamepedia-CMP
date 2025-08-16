package org.gamepdia.di




import org.gamepdia.coreDatabase.DatabaseDriverFactory
import org.gamepdia.repo.FavoriteRepoImpl
import org.gamepdia.repository.FavoriteRepository
import org.koin.dsl.module

fun favoriteModule() = module {
    single<FavoriteRepository> { FavoriteRepoImpl(get<DatabaseDriverFactory>()) }
}