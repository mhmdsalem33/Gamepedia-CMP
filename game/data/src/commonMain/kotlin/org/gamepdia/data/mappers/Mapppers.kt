package org.gamepdia.data.mappers

import org.gamepdia.coreNetwork.model.game_details.DeveloperResponse
import org.gamepdia.coreNetwork.model.game_details.GameDetailsResponse
import org.gamepdia.coreNetwork.model.game_details.PlatformWrapperResponse
import org.gamepdia.coreNetwork.model.game_details.StoreResponse
import org.gamepdia.coreNetwork.model.game_details.StoresResponse
import org.gamepdia.coreNetwork.model.game_details.TagResponse
import org.gamepdia.domain.model.Developer
import org.gamepdia.domain.model.GameDetails
import org.gamepdia.domain.model.Platform
import org.gamepdia.domain.model.Store
import org.gamepdia.domain.model.Stores
import org.gamepdia.domain.model.Tag


fun GameDetailsResponse.toDomainGameDetails(): GameDetails {
    return GameDetails(
        backgroundImage = this.background_image.orEmpty(),
        backgroundImageAdditional = this.background_image_additional,
        description = this.description?.stripHtml().orEmpty(),
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        platforms = this.platforms?.toDomainPlatform() ?: emptyList(),
        stores = this.stores?.map { it.toDomainStores() } ?: emptyList(),
        developer = this.developers?.map { it.toDomainDeveloper() } ?: emptyList(),
        tags = this.tags?.toDomainTags() ?: emptyList()
    )
}

fun List<PlatformWrapperResponse>.toDomainPlatform(): List<Platform> = map {
    Platform(
        name = it.platform?.name ?: "",
        image_background = it.platform?.image_background
    )
}


fun StoresResponse.toDomainStores(): Stores {
    return Stores(
        id = this.id ?: 0,
        store = this.store?.toDomainStore()  ?: Store(name =  "" , gamesCount = 0 ,  imageBackground = "" , domain = "")
    )
}



fun StoreResponse.toDomainStore(): Store {
   return Store(
        name = this.name ?: "",
        gamesCount =  this.games_count ?: -0,
        imageBackground = this.image_background ?: "",
       domain =  this.domain ?: ""
    )
}


fun DeveloperResponse.toDomainDeveloper(): Developer {
    return Developer(
        name = this.name ?: "",
        imageBackground = this.image_background ?: "",
        gamesCount = this.games_count ?: 0
    )
}

fun List<TagResponse>.toDomainTags(): List<Tag> = map {
    Tag(
        name = it.name,
        imageBackground = it.image_background,
        gamesCount = it.games_count
    )
}


fun String.stripHtml(): String {
    return this.replace(Regex("<.*?>"), "").trim()
}

