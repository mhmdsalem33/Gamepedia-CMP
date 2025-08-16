package org.gamepdia.usecase

import org.gamepdia.repository.FavoriteRepository

class DeleteGameUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id : Long ) = favoriteRepository.delete(id)
}