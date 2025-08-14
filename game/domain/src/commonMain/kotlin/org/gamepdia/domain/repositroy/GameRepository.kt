package org.gamepdia.domain.repositroy

import org.gamepdia.domain.model.Game
import org.gamepdia.domain.model.GameDetails

interface GameRepository {
    suspend fun getGames() : Result<List<Game>?>
    suspend fun getGameDetails( id : Int ) : Result<GameDetails?>
}