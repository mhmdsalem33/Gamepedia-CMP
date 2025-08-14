package org.gamepdia.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.gamepdia.repository.SearchRepository


class GetSearchGamesUseCase ( val searchRepository: SearchRepository ) {
    operator fun invoke( q : String ) = flow {
        emit(searchRepository.search(q))
    }.catch { e ->
        emit(Result.failure(e))
    }.flowOn(Dispatchers.IO)

}