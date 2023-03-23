package com.wantech.potter.characters.data

import com.wantech.potter.characters.data.datasource.CharactersItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PotterApi {

    @GET("characters")
    suspend fun getCharacters(): List<CharactersItem>

    suspend fun getCharacterInfor(id: String): CharactersItem

    @GET("house")
    suspend fun getCharactersByHouses(@Query("house") house: String): List<CharactersItem>
}