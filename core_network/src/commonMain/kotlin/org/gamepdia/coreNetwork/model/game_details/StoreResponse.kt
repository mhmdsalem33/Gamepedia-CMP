package org.gamepdia.coreNetwork.model.game_details

import kotlinx.serialization.Serializable



@Serializable
data class StoreResponse(
    val id: Int?,
    val games_count: Int? = null,
    val image_background: String? = null
)