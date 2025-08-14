package org.gamepdia.domain.repositroy

import org.gamepdia.domain.model.Game

interface GameRepository {
    suspend fun getGames() : Result<List<Game>?>
}