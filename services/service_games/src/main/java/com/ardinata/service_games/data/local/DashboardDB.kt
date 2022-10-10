package com.ardinata.service_games.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ardinata.service_games.data.local.DashboardDB.Companion.RoomVersion
import com.ardinata.service_games.data.local.dao.FavGameDao
import com.ardinata.service_games.domain.entity.GamesResultItemEntity

@Database(entities = [GamesResultItemEntity::class], version = RoomVersion)
abstract class DashboardDB : RoomDatabase() {
    abstract fun getFavGameDao() : FavGameDao
    companion object {
        const val RoomVersion = 1
        const val DBName = "Perqara_Test"
    }
}