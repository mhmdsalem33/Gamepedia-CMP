package org.gamepdia.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gamepdia.domain.model.Game
import org.gamepdia.usecases.GetSearchGamesUseCase


@OptIn(FlowPreview::class)
class SearchViewModel(  val getSearchGamesUseCase: GetSearchGamesUseCase ) : ViewModel() {

    private val _searchGamesUiState = MutableStateFlow(SearchScreen.UiState())
    val searchGamesUiState = _searchGamesUiState.asStateFlow()

    private val _query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _query.filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(500)
                .collectLatest { query ->
                    search(query)
                }
        }
    }


    fun updateQuery( query : String) {
        _query.update { query }
    }

    private fun search(query: String) =
        getSearchGamesUseCase(query)
            .onStart {
                _searchGamesUiState.update { SearchScreen.UiState(isLoading = true) }
            }.onEach { result: Result<List<Game>?> ->
                result.onSuccess { data ->
                    _searchGamesUiState.update { SearchScreen.UiState(data = data) }
                }.onFailure { e ->
                    _searchGamesUiState.update { SearchScreen.UiState(error = e.message ?: "") }
                }
            }.launchIn(viewModelScope)


}


data object SearchScreen {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<Game>? = null
    )
}