package com.wantech.potter.characters.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.wantech.potter.R
import com.wantech.potter.characters.data.datasource.CharactersItem

@Composable
fun PotterDetailItem(modifier: Modifier = Modifier, charactersItem: CharactersItem) {
    val painter = rememberAsyncImagePainter(
        model =
        if (charactersItem.image.length < 2) R.drawable.images else charactersItem.image
    )
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.padding(8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserImage(modifier = Modifier.size(160.dp), painter = painter)
                Divider(thickness = 2.dp)
                Text(
                    charactersItem.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = charactersItem.gender,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "House:${charactersItem.house}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(6.dp))
                if (charactersItem.actor.isNotEmpty() || charactersItem.actor.isNotBlank()) {
                    Text(
                        text = "Actor:${charactersItem.actor}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                if (charactersItem.ancestry.isNotEmpty() && charactersItem.dateOfBirth.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Origin:${charactersItem.ancestry}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = "Born:${charactersItem.dateOfBirth}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }

            }
        }
    }

}


@Composable
private fun UserImage(modifier: Modifier = Modifier, painter: Painter) {
    Surface(
        modifier = modifier
            .padding(8.dp),
        shape = CircleShape,
    ) {
        Image(
            painter = painter,
            contentDescription = "image"
        )
    }
}


