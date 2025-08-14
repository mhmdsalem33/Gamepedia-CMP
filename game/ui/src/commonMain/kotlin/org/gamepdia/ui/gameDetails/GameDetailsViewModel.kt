package org.gamepdia.ui.gameDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import org.gamepdia.domain.model.GameDetails
import org.gamepdia.domain.useCase.GetGameDetailsUseCase

class GameDetailsViewModel(
    private val getGameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel() {



    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    fun getGameDetails(id: Int) {
        getGameDetailsUseCase(id)
            .onStart {
                _uiState.update { UiState(isLoading = true) }
            }.onEach { result ->
                result.onSuccess { gameDetails ->
                    _uiState.update { UiState(data = gameDetails) }
                }.onFailure { error ->
                    _uiState.update { UiState(error = error.message) }
                }
            }.launchIn(viewModelScope)
    }

}


data class UiState(
    val isLoading: Boolean = false,
    val data: GameDetails? = null,
    val error: String? = null
)





