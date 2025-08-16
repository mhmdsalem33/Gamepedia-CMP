package org.gamepdia.domain.model



data class GameDetails(
    val backgroundImage: String,
    val backgroundImageAdditional: String?,
    val description: String = "",
    val id: Int ,
    val name: String = "",
    val platforms: List<Platform> = emptyList(),
    val stores: List<Stores> = emptyList(),
    val developer: List<Developer> =  emptyList(),
    val tags : List<Tag>

)


data class Platform(
    val name: String = "",
    val image_background: String? = null
)


data class Stores(
    val id: Int,
    val store : Store

)

data class Store(
    val name : String ,
    val gamesCount: Int,
    val imageBackground: String,
    val domain: String
)

data class Developer(
    val name : String ,
    val imageBackground: String,
    val gamesCount: Int,
)


data class Tag(
    val name : String,
    val imageBackground: String,
    val gamesCount: Int,
)
