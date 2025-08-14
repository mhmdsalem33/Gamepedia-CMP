package org.gamepdia.ui.gameDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.items
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


@Composable
fun GameDetailsScreen(id : String){

    val viewModel = koinViewModel<GameDetailsViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id){
        viewModel.getGameDetails(id.toInt())
    }

    GameDetailsScreenContent(modifier = Modifier.fillMaxSize() , uiState = uiState.value )

}

@Composable
fun GameDetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: UiState
){



    if (uiState.isLoading){
        GameDetailsLoading()
    }


    if (!uiState.error.isNullOrEmpty()){
        GameDetailsError(uiState.error)
    }


    uiState.data?.let { data ->

        LazyColumn(modifier = Modifier.fillMaxSize()){

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
                        .padding(horizontal = 12.dp , vertical = 12.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge

                )
            }

            //Description
            item {
                Text(
                    text = data.description,
                    modifier = Modifier
                        .padding(horizontal = 12.dp , vertical = 12.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall

                )
            }

            //Platforms
            item {
                Column( modifier = Modifier.fillMaxWidth() ){
                    Text(
                        text = "Platforms",
                        modifier = Modifier
                            .padding(horizontal = 12.dp , vertical = 12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    LazyRow(
                        contentPadding = PaddingValues(start = 10.dp),
                        modifier = Modifier
                        .padding(bottom = 30.dp)
                        .fillMaxWidth()){

                        items(data.platforms ?: emptyList() ){ platform ->
                            PlatformItem(platform)
                        }
                    }
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
    ){
        Column(modifier = Modifier
            .width(150.dp)
            .height(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = platform.image_background ,
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
fun GameDetailsItem(){

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
        logError(error )
    }

}