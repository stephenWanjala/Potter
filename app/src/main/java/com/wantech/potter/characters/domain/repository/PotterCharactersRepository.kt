package com.wantech.potter.characters.domain.repository

import com.wantech.potter.characters.data.datasource.CharactersItem
import kotlinx.coroutines.flow.Flow

interface PotterCharactersRepository {

    suspend fun getCharacters(): Flow<Result<List<CharactersItem>>>
    suspend fun getCharacterInfor(id: String): Result<CharactersItem>
}