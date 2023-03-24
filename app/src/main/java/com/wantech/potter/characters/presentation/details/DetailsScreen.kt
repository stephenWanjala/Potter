package com.wantech.potter.characters.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.characters.presentation.components.ProfileScreen

@Destination
@Composable
fun DetailsScreen(
    character: CharactersItem
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
            ) {
                ProfileScreen(character =character)
            }
        }

}