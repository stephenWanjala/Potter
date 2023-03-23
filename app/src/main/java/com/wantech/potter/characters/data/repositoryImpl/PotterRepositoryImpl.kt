package com.wantech.potter.characters.data.repositoryImpl

import com.wantech.potter.characters.data.PotterApi
import com.wantech.potter.characters.data.datasource.CharactersItem
import com.wantech.potter.characters.domain.repository.PotterCharactersRepository
import com.wantech.potter.core.util.Resource
import com.wantech.potter.core.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PotterRepositoryImpl @Inject constructor(
    private val api: PotterApi
) : PotterCharactersRepository {
    override suspend fun getCharacters(): Flow<Resource<List<CharactersItem>>> {
        return flow {
            try {
                emit(Resource.Loading())
                emit(Resource.Success(api.getCharacters()))
            } catch (e: Exception) {
                emit(Resource.Error(UiText.DynamicString(e.message ?: "Unknown Error")))
            }

        }
    }

    override suspend fun getCharacterInfor(id: String): Resource<CharactersItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharactersByHouses(house: String): Flow<Resource<List<CharactersItem>>> {
        return flow {
            try {
                emit(Resource.Loading())
                emit(Resource.Success(api.getCharactersByHouses(house)))
            } catch (e: Exception) {
                emit(Resource.Error(UiText.DynamicString(e.message ?: "Unknown Error")))
            }
        }
    }
}