package com.wantech.potter.characters.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wantech.potter.characters.data.mappers.toHarryCharacter
import com.wantech.potter.characters.presentation.home.components.MSearchBar
import com.wantech.potter.characters.presentation.home.components.PotterItem
import com.wantech.potter.core.util.Screen
import com.wantech.potter.core.util.hasInternetConnection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val hasInternet = rememberSaveable {
        mutableStateOf(context.hasInternetConnection())
    }
    val characters = viewModel.state.collectAsState().value.characters
    val state = viewModel.state.collectAsState()
    val lazyListState = rememberLazyListState()
    val showSearch = rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = lazyListState.isScrollInProgress) {
        showSearch.value = false
    }
    val topAppBarState = rememberTopAppBarState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showSearch.value) {
                MSearchBar(viewModel = viewModel,
                    toggleShowSearch = {
                        showSearch.value = !showSearch.value
                    }, showSearch = showSearch
                )
            } else {
                TopAppBar(title = { Text(text = "Harry Potter Characters") },
                    scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = topAppBarState),
                    modifier = Modifier.fillMaxWidth(), actions = {
                        TextButton(onClick = { showSearch.value = !showSearch.value }) {
                            Text(text = "Search")
                        }
                    })
            }

        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                AnimatedVisibility(!hasInternet.value) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.align(Center),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.WifiOff,
                                contentDescription = null,
                                modifier = Modifier.align(CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Please Check Your Internet Connection",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1f), contentPadding = paddingValues,
                    state = lazyListState
                ) {
                    items(characters) { character ->
                        PotterItem(
                            character = character.toHarryCharacter(),
                            onCharacterClick = { potter ->
                                navController.navigate(route = Screen.Details.route + "/${potter.characterId}")
                            })
                    }

                }
            }
        }
    }
}