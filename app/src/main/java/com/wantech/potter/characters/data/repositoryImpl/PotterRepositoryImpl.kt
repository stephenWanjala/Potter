package com.wantech.potter.characters.data.repositoryImpl

import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.characters.domain.repository.PotterCharactersRepository
import kotlinx.coroutines.flow.Flow

class PotterRepositoryImpl:PotterCharactersRepository {
    override suspend fun getCharacters(): Flow<Result<List<CharactersItem>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterInfor(id: String): Result<CharactersItem> {
        TODO("Not yet implemented")
    }
}