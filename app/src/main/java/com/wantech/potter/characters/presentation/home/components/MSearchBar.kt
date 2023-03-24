package com.wantech.potter.characters.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MSearchBar(
    modifier: Modifier = Modifier,
    toggleShowSearch: () -> Unit,
    showSearch: MutableState<Boolean>,
    selectedChip: MutableState<Int>,
    searchQuery: MutableState<String>
) {


    val chips = listOf(
        SearchBarChip(name = "None", id = 0),
        SearchBarChip(name = "Name", id = 1),
        SearchBarChip(name = "House", id = 2)
    )


    AnimatedVisibility(visible = showSearch.value) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {


            TextField(
                value = searchQuery.value,
                onValueChange = { value ->
                    searchQuery.value = value
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "search",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                },
                trailingIcon = {
                    if (searchQuery.value.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchQuery.value = ""
                                toggleShowSearch()
                                // Remove text from TextField when you press the 'X' icon
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "clear",
                                modifier = Modifier
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(4.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(text = "Search For Character(s)")
                },
            )




            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(chips) { chip ->

                    ElevatedFilterChip(
                        onClick = { selectedChip.value = chip.id },
                        label = {
                            Text(text = chip.name)
                        },
                        selected = selectedChip.value == chip.id,
                        leadingIcon = {
                            AnimatedVisibility(visible = selectedChip.value == chip.id) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = "selected",
                                    modifier = Modifier.padding(2.dp)
                                )
                            }


                        }
                    )
                }


            }

        }
    }


}


data class SearchBarChip(
    val id: Int,
    val name: String,
)

