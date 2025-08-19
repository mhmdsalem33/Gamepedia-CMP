package org.gamepdia.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.gamepdia.ui.GameItemCard
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onFavoriteClick: (() -> Unit) = {},
    onSearchClick: (() -> Unit),
    onCardClick: ((Int) -> Unit)
) {


    val viewModel = koinViewModel<GameViewModel>()
    val uiState = viewModel.gamesUiState.collectAsStateWithLifecycle()

    GameScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState.value,
        onFavoriteClick,
        onSearchClick,
        onCardClick
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UiState,
    onFavoriteClick: (() -> Unit),
    onSearchClick: (() -> Unit),
    onCardClick: ((Int) -> Unit)
) {


    if (uiState.isLoading) {
        GameLoading()
    }

    if (uiState.error.isNotBlank()) {
        GameError(uiState.error)
    }


    uiState.data?.let { data ->

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(data) { game ->
                    GameItemCard(game, onClick = onCardClick)
                }
            }
        }


        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 25.dp)
                .fillMaxWidth(),

            ) {

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    onSearchClick()
                },
                modifier = Modifier.background(color = Color.White, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = "",
                    modifier = Modifier.padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            IconButton(
                onClick = {
                    onFavoriteClick()
                },
                modifier = Modifier.background(color = Color.White, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite, contentDescription = "",
                    modifier = Modifier.padding(4.dp)
                )
            }


        }
    }
}


@Composable
fun GameLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color.Blue)
    }
}

@Composable
fun GameError(error: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(error)
    }

}