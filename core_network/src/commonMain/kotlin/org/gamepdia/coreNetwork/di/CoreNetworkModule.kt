package org.gamepdia.coreNetwork.di

import org.gamepdia.coreNetwork.apiService.ApiService
import org.gamepdia.coreNetwork.client.KtorClient
import org.koin.dsl.module

fun getCoreNetworkModule() = module {
    single {
        ApiService(httpClient =  KtorClient.getInstance())
    }
}