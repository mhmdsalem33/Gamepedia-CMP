package org.gamepdia.coreNetwork.model.game_details

import kotlinx.serialization.Serializable

@Serializable
data class DeveloperResponse(
    val name : String ? = null,
    val image_background : String ? = null,
    val games_count : Int ? = null
)
