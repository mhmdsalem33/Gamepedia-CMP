package org.gamepdia.repository

import kotlinx.coroutines.flow.Flow
import org.gamepdia.domain.model.Game

interface FavoriteRepository{

    fun getAllGames(): Flow<List<Game>>

    suspend fun upsert(id : Long , image : String , name : String )

    suspend fun delete(id: Long)


}