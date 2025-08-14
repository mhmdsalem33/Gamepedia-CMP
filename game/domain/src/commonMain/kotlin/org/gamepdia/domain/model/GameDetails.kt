package org.gamepdia.domain.model



data class GameDetails(
    val backgroundImage: String?,
    val backgroundImageAdditional: String?,
    val description: String = "",
    val id: Int?,
    val name: String = "",
    val platforms: List<Platform>? = emptyList(),
    val stores: List<Store>?,
    val developer: List<Developer>?,
    val tags : List<Tag>?

)


data class Platform(
    val name: String = "",
    val image_background: String? = null
)


data class Store(
    val id: Int?,
    val gamesCount: Int?,
    val imageBackground: String?
)


data class Developer(
    val name : String ?,
    val imageBackground: String?,
    val gamesCount: Int?,
)


data class Tag(
    val name : String?,
    val imageBackground: String?,
    val gamesCount: Int?,
)
