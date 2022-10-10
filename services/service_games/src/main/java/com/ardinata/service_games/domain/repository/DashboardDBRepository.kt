package com.ardinata.service_games.domain.repository

import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.test.perqara.core.model.Result

interface DashboardDBRepository{
   suspend fun insertFavGame(game: GamesResultItemEntity) : Result<Long>
   suspend fun getFavGameList() : Result<List<GamesResultItemEntity>>
   suspend fun deleteGameFromFavorite(id: Long) : Result<Int>
   suspend fun deleteFavGameList() : Result<Int>
}