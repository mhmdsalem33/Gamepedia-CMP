package org.gamepdia.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.gamepdia.domain.model.Game


@Composable
fun GameItemCard(
    game: Game,
    onClick: (Int) -> Unit,
    isDeleteShow : Boolean = false,
    onDeleteItemClick : (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier.padding(8.dp)
            .clickable {
                onClick(game.id)
            },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(Modifier.fillMaxSize()
            .background(Color.White)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                model = game.imageUrl,
                contentDescription = game.name,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }

            if (isDeleteShow){
                IconButton(
                    onClick = { onDeleteItemClick(game.id) } ,
                    modifier = Modifier
                        .padding(12.dp)
                        .background( color = Color.White , shape = CircleShape )
                        .align(alignment = Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete , contentDescription = "",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

