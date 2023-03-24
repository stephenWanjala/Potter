package com.wantech.potter.characters.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.wantech.potter.R
import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.characters.data.datasource.Wand

@Composable
fun ProfileScreen(character: CharactersItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Actor: ${character.actor}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Gender: ${character.gender}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "House: ${character.house}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Species: ${character.species}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Wand:",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Wood: ${character.wand.wood}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Core: ${character.wand.core}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Length: ${character.wand.length} inches",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


val character = CharactersItem(
    actor = "Daniel Radcliffe",
    alive = true,
    alternate_actors = listOf("Elijah Wood", "Toby Regbo"),
    alternate_names = listOf("The Boy Who Lived", "The Chosen One"),
    ancestry = "Half-blood",
    dateOfBirth = "31 July, 1980",
    eyeColour = "Green",
    gender = "Male",
    hairColour = "Black",
    hogwartsStaff = false,
    hogwartsStudent = true,
    house = "Gryffindor",
    id = "5a0fa54aae5bc100213c2330",
    image = R.drawable.images.toString(),
    name = "Harry Potter",
    patronus = "Stag",
    species = "Human",
    wand = Wand(core = "Phoenix feather", length = 11.0, wood = "Holly"),
    wizard = true,
    yearOfBirth = 1980
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevProfile() {
    ProfileScreen(character = character)
}


