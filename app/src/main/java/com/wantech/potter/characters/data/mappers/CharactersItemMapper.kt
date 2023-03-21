package com.wantech.potter.characters.data.mappers

import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.characters.domain.model.HarryCharacter

fun CharactersItem.toHarryCharacter(): HarryCharacter =
    HarryCharacter(
        characterId = id,
        name = name,
        image = image,
        gender = gender,
        ancestry = ancestry
    )