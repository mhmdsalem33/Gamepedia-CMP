package org.gamepdia.domain.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.gamepdia.domain.repositroy.GameRepository

class GetGameDetailsUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(id: Int) = flow {
        emit(gameRepository.getGameDetails(id))
    }.catch {
        error -> emit(Result.failure(error))
    }
    .flowOn(Dispatchers.IO)
}