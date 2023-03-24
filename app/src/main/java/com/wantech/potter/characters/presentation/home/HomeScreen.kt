package com.wantech.potter.characters.presentation.home

import androidx.compose.animation.AnimatedVisibility
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wantech.potter.characters.data.mappers.toHarryCharacter
import com.wantech.potter.characters.presentation.destinations.DetailsScreenDestination
import com.wantech.potter.characters.presentation.home.components.MSearchBar
import com.wantech.potter.characters.presentation.home.components.PotterEvents
import com.wantech.potter.characters.presentation.home.components.PotterItem
import com.wantech.potter.core.util.hasInternetConnection
import java.util.*

@Destination(start = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val hasInternet = rememberSaveable {
        mutableStateOf(context.hasInternetConnection())
    }
    var characters = viewModel.state.collectAsState().value.characters
    val state = viewModel.state.collectAsState()
    val lazyListState = rememberLazyListState()
    val showSearch = rememberSaveable {
        mutableStateOf(false)
    }
    val searchQuery = remember {
        mutableStateOf("")
    }
    val selectedChip = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = lazyListState.isScrollInProgress) {
        showSearch.value = false
    }

    LaunchedEffect(key1 = searchQuery.value) {
        when (selectedChip.value) {
            0 -> {
                characters = characters.filter {
                    it.name.lowercase(Locale.ROOT)
                        .contains(searchQuery.value.lowercase(Locale.ROOT))
                }
            }
            1 -> {
                characters = characters.filter {
                    it.name.lowercase(Locale.ROOT)
                        .contains(searchQuery.value.lowercase(Locale.ROOT))
                }

            }
            2 -> {

                viewModel.onEvent(
                    PotterEvents.GetCharactersByName(
                        searchQuery.value.lowercase(
                            Locale.ROOT
                        )
                    )
                )
                characters = characters.filter {
                    it.house.lowercase(Locale.ROOT)
                        .contains(searchQuery.value.lowercase(Locale.ROOT))
                }
            }
        }
    }
    val topAppBarState = rememberTopAppBarState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showSearch.value) {
                MSearchBar(
                    toggleShowSearch = {
                        showSearch.value = !showSearch.value
                    },
                    showSearch = showSearch, selectedChip = selectedChip,
                    searchQuery = searchQuery
                )
            } else {
                TopAppBar(title = { Text(text = "Harry Potter Characters") },
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState),
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
                            onCharacterClick = {

                                navigator.navigate(DetailsScreenDestination.invoke(character)) {
//                                    this.popUpTo(DetailsScreenDestination.route){
//                                        inclusive = true
//                                    }
                                }

                            })
                    }

                }
            }
        }
    }
}