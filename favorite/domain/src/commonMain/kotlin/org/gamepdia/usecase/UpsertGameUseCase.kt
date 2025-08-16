package org.gamepdia.usecase

import org.gamepdia.repository.FavoriteRepository

class UpsertGameUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend  operator fun invoke(id : Long  , image : String , name : String) = favoriteRepository.upsert(id , image , name)
}