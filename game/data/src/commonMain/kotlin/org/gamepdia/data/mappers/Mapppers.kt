package org.gamepdia.data.mappers

import org.gamepdia.coreNetwork.model.game_details.DeveloperResponse
import org.gamepdia.coreNetwork.model.game_details.GameDetailsResponse
import org.gamepdia.coreNetwork.model.game_details.PlatformWrapperResponse
import org.gamepdia.coreNetwork.model.game_details.StoreResponse
import org.gamepdia.coreNetwork.model.game_details.TagResponse
import org.gamepdia.domain.model.Developer
import org.gamepdia.domain.model.GameDetails
import org.gamepdia.domain.model.Platform
import org.gamepdia.domain.model.Store
import org.gamepdia.domain.model.Tag


fun GameDetailsResponse.toDomainGameDetails(): GameDetails {
    return GameDetails(
        backgroundImage = this.background_image,
        backgroundImageAdditional = this.background_image_additional,
        description = this.description?.stripHtml().orEmpty(),
        id = this.id,
        name = this.name.orEmpty() ,
        platforms = this.platforms?.toDomainPlatform(),
        stores = this.stores?.map { it.toDomainStore() },
        developer = this.developer?.map { it.toDomainDeveloper() },
        tags = this.tags?.toDomainTags()
    )
}

fun List<PlatformWrapperResponse>.toDomainPlatform(): List<Platform> = map {
    Platform(
        name = it.platform?.name ?: "",
        image_background = it.platform?.image_background
    )
}


fun StoreResponse.toDomainStore(): Store {
    return Store(
        id = this.id,
        gamesCount = this.games_count,
        imageBackground = this.image_background
    )
}


fun DeveloperResponse.toDomainDeveloper(): Developer {
    return Developer(
        name = this.name,
        imageBackground = this.image_background,
        gamesCount = this.games_count
    )
}

fun List<TagResponse>.toDomainTags() : List<Tag> =  map {
    Tag(
        name = it.name,
        imageBackground = it.image_background,
        gamesCount = it.games_count
    )
}



fun String.stripHtml(): String {
    return this.replace(Regex("<.*?>"), "").trim()
}

