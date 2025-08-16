package org.gamepdia.ui.gameDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gamepdia.domain.model.GameDetails
import org.gamepdia.domain.useCase.GetGameDetailsUseCase
import org.gamepdia.usecase.DeleteGameUseCase
import org.gamepdia.usecase.UpsertGameUseCase

class GameDetailsViewModel(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val upsertGameUseCase: UpsertGameUseCase,
    private val deleteGameUseCase: DeleteGameUseCase,
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

    fun saveGame(id: Long, image: String, name: String) = viewModelScope.launch {
        upsertGameUseCase( id = id, image = image, name = name)
    }



    fun delete(id : Long) = viewModelScope.launch {
        deleteGameUseCase(id)
    }

}


data class UiState(
    val isLoading: Boolean = false,
    val data: GameDetails? = null,
    val error: String? = null
)





