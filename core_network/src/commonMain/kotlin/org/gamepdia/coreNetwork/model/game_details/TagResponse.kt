package org.gamepdia.coreNetwork.model.game_details

import kotlinx.serialization.Serializable

@Serializable
data class TagResponse(
    val name : String,
    val image_background: String,
    val games_count : Int
)
