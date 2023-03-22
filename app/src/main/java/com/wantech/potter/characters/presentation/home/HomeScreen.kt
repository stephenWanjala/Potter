package com.wantech.potter.characters.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.wantech.potter.characters.domain.model.HarryCharacter
import com.wantech.potter.characters.presentation.home.components.PotterItem
import com.wantech.potter.core.util.Screen
import com.wantech.potter.core.util.hasInternetConnection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val hasInternet = rememberSaveable {
        mutableStateOf(context.hasInternetConnection())
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = " Harry Potter characters",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            }, scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior())
        }) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize()) {
            /*if (hasInternet.value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Please Check Your Internet Connection",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                    )
                }
            }*/

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f), contentPadding = paddingValues
            ) {
                item {
                    PotterItem(
                        character = HarryCharacter(
                            characterId = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
                            name =
                            "Harry Potter",
                            image = "https://ik.imagekit.io/hpapi/hermione.jpeg",
                            gender = "male",
                            ancestry = "half-blood"
                        ),
                        onCharacterClick = {
                            navController.navigate(Screen.Details.route + "/${it.characterId}") {

                            }
                        }
                    )
                }


            }
        }
    }
}