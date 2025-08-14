package org.gamepdia.coreNetwork.apiService

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import org.gamepdia.coreNetwork.model.game.GameResponse
import org.gamepdia.coreNetwork.model.game_details.GameDetailsResponse

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


    suspend fun getGameDetails( id : Int ) : Result<GameDetailsResponse>{
        return try {
            val response = httpClient.get{
                url {
                    appendPathSegments("games", "$id")
                }
            }
                .body<GameDetailsResponse>()
            Result.success(response)
        }catch ( e : Exception){
            Result.failure(e)
        }
    }



}