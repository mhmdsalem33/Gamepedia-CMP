package org.gamepdia.repository

import org.gamepdia.coreNetwork.apiService.ApiService
import org.gamepdia.domain.model.Game
import org.gamepdia.mappers.toDomainListOfGames

class SearchRepositoryImpl(private val apiService: ApiService) : SearchRepository {
    override suspend fun search(q: String): Result<List<Game>?> {
        return try {
            val response = apiService.search(q)
            Result.success(response.getOrThrow().results?.toDomainListOfGames())
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}