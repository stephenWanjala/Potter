package com.wantech.potter.characters.domain.repository

import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface PotterCharactersRepository {

    suspend fun getCharacters(): Flow<Resource<List<CharactersItem>>>
    suspend fun getCharacterInfor(id: String): Resource<CharactersItem>
}