package org.gamepdia.usecase

import org.gamepdia.repository.FavoriteRepository

class GetFavoriteGamesUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() = favoriteRepository.getAllGames()
}