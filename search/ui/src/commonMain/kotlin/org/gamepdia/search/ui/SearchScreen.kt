package org.gamepdia.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.gamepdia.logError
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.layout.Column
import org.gamepdia.domain.model.Game
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale


@Composable
fun SearchScreen(modifier: Modifier = Modifier) {


    val viewModel = koinViewModel<SearchViewModel>()
    val uiState = viewModel.searchGamesUiState.collectAsStateWithLifecycle()
    val query = rememberSaveable { mutableStateOf("") }

    SearchScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState.value,
        query = query.value,
        onQueryChange = {
            query.value = it
            viewModel.updateQuery(query.value)
        }
    )


}

@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    uiState: SearchScreen.UiState,
    query: String,
    onQueryChange: (String) -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            placeholder = {
                Text("Search here...")
            }
        )

        if (uiState.isLoading) {
            SearchLoading()
        }

        if (uiState.error.isNotBlank()) {
            SearchError(uiState.error)
        }

        uiState.data?.let { data ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(data) { game ->
                    SearchItemCard(game)
                }
            }
        }
    }

}



@Composable
fun SearchItemCard(game: Game) {
    AsyncImage(
        modifier =
            Modifier.padding(12.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .height(250.dp)

        ,
        model = game.imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun SearchLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


@Composable
fun SearchError(error: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(error)
        logError(error)
    }

}