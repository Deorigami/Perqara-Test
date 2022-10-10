package com.ardinata.service_games.domain.repository

import com.ardinata.service_games.domain.entity.GameDetailsEntity
import com.ardinata.service_games.domain.entity.GamesQueriesEntity
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.test.perqara.core.model.Result

interface DashboardServiceRepository {
    suspend fun getGamesList(param : GamesQueriesEntity) : Result<List<GamesResultItemEntity>>
    suspend fun searchGameList(param : GamesQueriesEntity) : Result<List<GamesResultItemEntity>>
    suspend fun getGameDetails(id: String) : Result<GameDetailsEntity>
}