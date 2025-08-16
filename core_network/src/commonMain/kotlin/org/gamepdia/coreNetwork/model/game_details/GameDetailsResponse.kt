package org.gamepdia.coreNetwork.model.game_details

import kotlinx.serialization.Serializable



@Serializable
data class GameDetailsResponse(
    val background_image: String? = null,
    val background_image_additional: String? = null,
    val description: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val platforms: List<PlatformWrapperResponse>? = emptyList(),
    val stores: List<StoresResponse>? = null,
    val developers: List<DeveloperResponse>? = null,
    val tags : List<TagResponse>? = null

)


@Serializable
data class PlatformWrapperResponse(
    val platform: PlatformDataResponse? = null
)

@Serializable
data class PlatformDataResponse(
    val id: Int? = null,
    val name: String? = null,
    val image_background: String? = null
)