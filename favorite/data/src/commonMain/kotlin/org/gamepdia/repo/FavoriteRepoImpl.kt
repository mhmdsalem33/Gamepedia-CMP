package org.gamepdia.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.gamepdia.coreDatabase.AppDatabase
import org.gamepdia.coreDatabase.DatabaseDriverFactory
import org.gamepdia.domain.model.Game
import org.gamepdia.repository.FavoriteRepository

class FavoriteRepoImpl(
    driverFactory: DatabaseDriverFactory

) : FavoriteRepository {

    private val database = AppDatabase.Companion(
        driverFactory.createDriver()
    )
    private val queries = database.appDatabaseQueries

    override fun getAllGames(): Flow<List<Game>> = flow {
        val games = queries.getAllGames().executeAsList()
        val map = games.map { Game(it.id.toInt(), it.image, it.name) }
        emit(map)
    }

    override suspend fun upsert(id: Long, image: String, name: String) {
        queries.upsert(id, image, name)
    }

    override suspend fun delete(id: Long)  =  queries.delete(id)

}