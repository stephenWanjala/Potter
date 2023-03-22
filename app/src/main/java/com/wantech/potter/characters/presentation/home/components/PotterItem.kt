package com.wantech.potter.characters.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.wantech.potter.characters.domain.model.HarryCharacter

@Composable
fun PotterItem(
    character: HarryCharacter,
    modifier: Modifier = Modifier,
    onCharacterClick: (HarryCharacter) -> Unit
) {
    val painter =
        rememberAsyncImagePainter(model = character.image, contentScale = ContentScale.Crop)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCharacterClick(character) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = character.gender)

                    Text(text = "~${character.ancestry}", textAlign = TextAlign.Center)
                }

            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CharacterItemPreview() {
    PotterItem(
        character = HarryCharacter(
            characterId = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
            name =
            "Harry Potter",
            image = "https://ik.imagekit.io/hpapi/harry.jpg",
            gender = "male",
            ancestry = "half-blood"
        ),
        onCharacterClick = {

        }
    )
}