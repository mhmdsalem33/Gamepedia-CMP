package org.gamepdia.coreNetwork.apiService

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.gamepdia.coreNetwork.model.game.GameResponse

class ApiService(
    val httpClient: HttpClient,
) {

    suspend fun getGames(): Result<GameResponse> {
        return try {
            val response = httpClient.get("games").body<GameResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun search( q: String ) : Result<GameResponse>{
        return try {
            val response = httpClient.get("games") { url { parameter("search", q ) } }.body<GameResponse>()
            Result.success(response)
        }catch ( e : Exception){
            Result.failure(e)
        }
    }


}