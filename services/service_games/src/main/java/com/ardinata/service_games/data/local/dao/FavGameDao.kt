package com.ardinata.service_games.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ardinata.service_games.domain.entity.GamesResultItemEntity

@Dao
interface FavGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavGame(game: GamesResultItemEntity) : Long

    @Query("SELECT * FROM FAV_GAME")
    suspend fun getFavGameList() : List<GamesResultItemEntity>

    @Query("DELETE FROM FAV_GAME WHERE id=:id")
    suspend fun deleteFromFav(id: Long) : Int

    @Query("DELETE FROM FAV_GAME")
    suspend fun deleteFavGameList() : Int
}