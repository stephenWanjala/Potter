package com.wantech.potter.characters.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ramcosta.composedestinations.annotation.Destination
import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.characters.presentation.components.ProfileScreen
import com.wantech.potter.characters.presentation.details.components.PotterDetailItem

@Destination
@Composable
fun DetailsScreen(
    character: CharactersItem
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
//                PotterDetailItem(charactersItem = character)
                ProfileScreen(character =character)
            }
        }
    }
}