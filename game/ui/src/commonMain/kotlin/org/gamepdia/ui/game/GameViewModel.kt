package org.gamepdia.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import org.gamepdia.domain.model.Game
import org.gamepdia.domain.useCase.GetGamesUseCase

class GameViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {


    private val _gamesUiState = MutableStateFlow(GameScreen.UiState())
    val gamesUiState = _gamesUiState.asStateFlow()


    init {
        getGames()
    }


    private fun getGames() = getGamesUseCase()
        .onStart {
            _gamesUiState.update { GameScreen.UiState(isLoading = true) }
        }.onEach { result ->

            result.onSuccess { data ->
                _gamesUiState.update { GameScreen.UiState(data = data) }
            }.onFailure { error ->
                _gamesUiState.update {
                    GameScreen.UiState(error = error.message.toString())
                }
            }
        }.launchIn(viewModelScope)
}




object GameScreen {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<Game>? = null
    )
}