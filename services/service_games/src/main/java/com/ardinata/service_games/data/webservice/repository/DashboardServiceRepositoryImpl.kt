package com.ardinata.service_games.data.webservice.repository

import com.ardinata.service_games.data.webservice.mapper.GameDetailsDtoMapper
import com.ardinata.service_games.data.webservice.mapper.GameListResultDtoMapper
import com.ardinata.service_games.data.webservice.mapper.GamesQueriesMapper
import com.ardinata.service_games.data.webservice.service.GamesService
import com.ardinata.service_games.domain.entity.GameDetailsEntity
import com.ardinata.service_games.domain.entity.GamesQueriesEntity
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.service_games.domain.repository.DashboardServiceRepository
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class DashboardServiceRepositoryImpl @Inject constructor(
    private val api: GamesService,
    private val githubResultDtoMapper: GameListResultDtoMapper,
    private val gamesQueriesMapper: GamesQueriesMapper,
    private val gameDetailsDtoMapper: GameDetailsDtoMapper
) : DashboardServiceRepository {
    override suspend fun getGamesList(param: GamesQueriesEntity): Result<List<GamesResultItemEntity>> {
        return githubResultDtoMapper(
            api.getGamesList(
                gamesQueriesMapper(param)
            )
        )
    }

    override suspend fun searchGameList(param: GamesQueriesEntity): Result<List<GamesResultItemEntity>> {
        return githubResultDtoMapper(
            api.searchGames(
                gamesQueriesMapper(param)
            )
        )
    }

    override suspend fun getGameDetails(id: String): Result<GameDetailsEntity> {
        return gameDetailsDtoMapper(
            api.getGameDetail(id)
        )
    }
}