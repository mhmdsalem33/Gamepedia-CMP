package org.gamepdia.favorite.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.gamepdia.domain.model.Game
import org.gamepdia.ui.GameItemCard
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onClick: (Int) -> Unit,
) {

    val viewModel = koinViewModel<FavoriteViewModel>()
    val games = viewModel.games.collectAsStateWithLifecycle()


    FavoriteScreenContent(
        modifier = modifier,
        games = games.value,
        onBackClick = {
            onBackClick()
        },
        onClick = { id -> onClick(id)  },
        onDeleteItemCLick = { id -> viewModel.delete(id.toLong()) },
    )
}


@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    games: List<Game>,
    onBackClick: () -> Unit,
    onClick: (Int) -> Unit,
    onDeleteItemCLick: (Int) -> Unit,
) {

    if (games.isEmpty()) {
        EmptyGames()
    }

    if (games.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(games) { game ->
                GameItemCard(
                    game,
                    onClick = {
                        onClick(game.id)
                    },
                    isDeleteShow = true,
                    onDeleteItemClick = {
                        onDeleteItemCLick(game.id)
                    })
            }
        }
    }
}


@Composable
fun EmptyGames() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Nothing found",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

