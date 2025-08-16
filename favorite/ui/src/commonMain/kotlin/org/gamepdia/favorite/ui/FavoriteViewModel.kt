package org.gamepdia.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.gamepdia.usecase.DeleteGameUseCase
import org.gamepdia.usecase.GetFavoriteGamesUseCase

class FavoriteViewModel(
    private val getFavoriteGamesUseCase: GetFavoriteGamesUseCase,
    private val deleteGameUseCase: DeleteGameUseCase
) : ViewModel(){


    val games = getFavoriteGamesUseCase.invoke().stateIn(
        viewModelScope , SharingStarted.WhileSubscribed() , emptyList()
    )


    fun delete(id  : Long ) = viewModelScope.launch {
        deleteGameUseCase(id)
    }

}