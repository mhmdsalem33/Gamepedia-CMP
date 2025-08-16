package org.gamepdia.ui.gameDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.gamepdia.domain.model.Platform
import org.gamepdia.logError
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Forward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import org.gamepdia.domain.model.Developer
import org.gamepdia.domain.model.Stores
import org.gamepdia.domain.model.Tag


@Composable
fun GameDetailsScreen(
    id: String,
    onBackPress : () ->  Unit
    ) {

    val viewModel = koinViewModel<GameDetailsViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getGameDetails(id.toInt())
    }

    GameDetailsScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState.value,
        onDelete = {

        },
        onSave = { id , name , image ->

        },
        onBackPress = { onBackPress() }
    )

}

@Composable
fun GameDetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onDelete: (Int) -> Unit,
    onSave: (id: Int, title: String, image: String) -> Unit,
    onBackPress: () -> Unit
) {


    if (uiState.isLoading) {
        GameDetailsLoading()
    }


    if (!uiState.error.isNullOrEmpty()) {
        GameDetailsError(uiState.error)
    }


    uiState.data?.let { data ->

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                // Head Image
                item {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        model = data.backgroundImage,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

                // Name
                item {
                    Text(
                        text = data.name,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )
                }

                //Description
                item {
                    Text(
                        text = data.description,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                //Platforms
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Platforms",
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 12.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        LazyRow(
                            contentPadding = PaddingValues(start = 10.dp),
                            modifier = Modifier
                                .padding(bottom = 30.dp)
                                .fillMaxWidth()
                        ) {

                            items(data.platforms) { platform ->
                                PlatformItem(platform)
                            }
                        }
                    }
                }

                // Start Stores
                item {
                    Text(
                        text = "Stores",
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(data.stores) { store ->
                    StoreItem(store)
                }

                // End Stores


                // Start Tags
                item {
                    Text(
                        text = "Tags",
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                item {
                    FlowRow(
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp).fillMaxWidth()
                    ) {
                        data.tags.forEach { tag ->
                            TagsItem(tag)
                        }
                    }
                }

                // End Tags


                // Start Developers
                item {
                    Text(
                        text = "Developers",
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                items(data.developer) { developer ->
                    DeveloperItem(developer)
                }

                // End Developers


            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 36.dp)
                    .fillMaxWidth(),

                ) {
                IconButton(
                    onClick = {
                        onBackPress()
                    },
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "",
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        onSave(data.id, data.name, data.backgroundImage)
                    },
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite, contentDescription = "",
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                IconButton(
                    onClick = {
                        onDelete(data.id)
                    },
                    modifier = Modifier.background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = "",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

        }
    }
}


@Composable
fun PlatformItem(platform: Platform) {
    Card(
        modifier = Modifier.wrapContentSize().padding(end = 5.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEFEFEF) // your background color
        )
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = platform.image_background,
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = Color.Transparent,
                        shape = CircleShape
                    ).clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = platform.name,
                style = MaterialTheme.typography.bodySmall
            )


        }

    }
}

@Composable
fun StoreItem(store: Stores) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(start = 12.dp, end = 12.dp, bottom = 5.dp)
    ) {
        AsyncImage(
            model = store.store.imageBackground,
            contentDescription = "",
            modifier = Modifier.size(120.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                ).clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = store.store.name,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 14.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = store.store.domain,
                style = MaterialTheme.typography.labelLarge,
                textDecoration = TextDecoration.Underline,
                fontSize = 13.sp,

                )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Game-count: ${store.store.gamesCount}",
                style = MaterialTheme.typography.labelSmall,
                textDecoration = TextDecoration.Underline,
                fontSize = 11.sp,
            )
        }
    }
}

@Composable
fun TagsItem(tag: Tag) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, end = 12.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(200.dp)
            ).border(width = .5.dp, color = Color.LightGray, shape = CircleShape)
            .clip(CircleShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(35.dp)
                .background(color = Color.Transparent, shape = CircleShape)
                .clip(CircleShape),
            model = tag.imageBackground,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = tag.name,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun DeveloperItem(developer: Developer) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        AsyncImage(
            model = developer.imageBackground,
            contentDescription = "developer image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)).size(120.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = developer.name,
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Game count : ${developer.gamesCount}",
                fontSize = 12.sp,
            )
        }
    }
}


@Composable
fun GameDetailsLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun GameDetailsError(error: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(error)
        logError(error)
    }

}