package org.gamepdia.coreNetwork.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.http.takeFrom
import io.ktor.client.plugins.logging.*
import io.ktor.http.HttpHeaders


object KtorClient {

    private const val API_KEY = "0a6421ee9d7f4494aba568c945dfa4c6"

    fun getInstance() : HttpClient = HttpClient {

        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true // <-- replaces nulls with defaults
            })
        }


        install(Logging) {
            logger = Logger.DEFAULT        // SLF4J on JVM, use Logger.SIMPLE on Native
            level = LogLevel.BODY          // logs URL, headers, and body
            sanitizeHeader { it == HttpHeaders.Authorization || it == HttpHeaders.Cookie }
        }

//        https://api.rawg.io/api/games?key=0a6421ee9d7f4494aba568c945dfa4c6

        install(DefaultRequest) {
            url.takeFrom("https://api.rawg.io/api/")
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            url.parameters.append("key", API_KEY)
        }


        install(HttpTimeout) {
            socketTimeoutMillis = 5000
            connectTimeoutMillis = 15000
            requestTimeoutMillis = 15000
        }

    }
}