package org.gamepdia.coreDatabase.di

import org.gamepdia.coreDatabase.DatabaseDriverFactory
import org.gamepdia.coreDatabase.getPlatformDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

fun databaseModule() : Module = module {
    single<DatabaseDriverFactory> { getPlatformDriverFactory() }
}



