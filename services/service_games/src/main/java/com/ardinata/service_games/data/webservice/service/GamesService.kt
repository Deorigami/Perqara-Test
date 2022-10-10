package com.ardinata.service_games.data.webservice.service

import com.ardinata.service_games.data.webservice.dto.GameDetailsDto
import com.ardinata.service_games.data.webservice.dto.GamesResultItemDto
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.test.perqara.core.model.ResultDto
import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GamesService {
    @GET("games")
    suspend fun getGamesList(
        @QueryMap queries : Map<String, String>
    ) : ResultDto<List<GamesResultItemDto>>

    @GET("games")
    suspend fun searchGames(
        @QueryMap queries : Map<String, String>
    ) : ResultDto<List<GamesResultItemDto>>

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id : String
    ) : GameDetailsDto
}