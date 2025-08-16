package org.gamepdia.coreNetwork.model.game_details

import kotlinx.serialization.Serializable


@Serializable
data class StoresResponse(
    val id: Int? = null,
    val store : StoreResponse ? = null
)

@Serializable
data class StoreResponse(
    val games_count: Int? = null,
    val image_background: String? = null,
    val name : String? = null,
    val domain: String ? = null
)