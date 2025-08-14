package org.gamepdia.mappers

import org.gamepdia.coreNetwork.model.game.Result
import org.gamepdia.domain.model.Game


fun List<Result>.toDomainListOfGames(): List<Game> = map {
    Game(
        id = it.id ?: 1,
        name = it.name ?: "",
        imageUrl = it.backgroundImage ?: ""
    )
}