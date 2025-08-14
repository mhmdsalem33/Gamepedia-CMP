package org.gamepdia.data.repository

import org.gamepdia.coreNetwork.apiService.ApiService
import org.gamepdia.data.mappers.toDomainGameDetails
import org.gamepdia.domain.model.Game
import org.gamepdia.domain.model.GameDetails
import org.gamepdia.domain.repositroy.GameRepository
import org.gamepdia.mappers.toDomainListOfGames

class GameRepositoryImpl(
    private val apiService: ApiService
) : GameRepository {

    override suspend fun getGames(): Result<List<Game>?> {
        val result = apiService.getGames()
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().results?.toDomainListOfGames())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }

    override suspend fun getGameDetails(id: Int): Result<GameDetails?> {
        val result = apiService.getGameDetails(id)
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().toDomainGameDetails())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }

}