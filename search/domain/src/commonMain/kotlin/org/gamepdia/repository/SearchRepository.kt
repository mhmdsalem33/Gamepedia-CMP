package org.gamepdia.repository

import org.gamepdia.domain.model.Game

interface SearchRepository {
    suspend fun search(q: String) : Result<List<Game>?>
}