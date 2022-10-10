package com.ardinata.service_games.data.local.repository

import com.ardinata.service_games.data.local.dao.FavGameDao
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.service_games.domain.repository.DashboardDBRepository
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class DashboardDBRepositoryImpl @Inject constructor(
    private val dao: FavGameDao
) : DashboardDBRepository {
    override suspend fun insertFavGame(game: GamesResultItemEntity): Result<Long> {
        return Result(dao.insertFavGame(game))
    }

    override suspend fun getFavGameList(): Result<List<GamesResultItemEntity>> {
        return Result(dao.getFavGameList())
    }

    override suspend fun deleteGameFromFavorite(id: Long): Result<Int> {
        return Result(dao.deleteFromFav(id))
    }

    override suspend fun deleteFavGameList(): Result<Int> {
        return Result(dao.deleteFavGameList())
    }

}