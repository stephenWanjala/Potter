package com.wantech.potter.characters.presentation.home.components

import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.core.util.UiText

data class CharactersState(
    val characters: List<CharactersItem> = emptyList(),
    val characterInfor: CharactersItem? = null,
    val charactersByHouses: List<CharactersItem> = emptyList(),
    val charactersBySearch: List<CharactersItem> = emptyList(),
    val charactersByName: List<CharactersItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = UiText.unknownError()
)



