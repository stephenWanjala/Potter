package com.wantech.potter.characters.presentation.home.components

sealed interface PotterEvents {
    object GetCharacters : PotterEvents
    data class GetCharacterInfor(val id: String) : PotterEvents
    data class GetCharactersByHouses(val house: String) : PotterEvents
    data class GetCharactersBySearch(val search: String) : PotterEvents
    data class GetCharactersByName(val filter: String) : PotterEvents
}