package com.ardinata.service_games.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAV_GAME")
data class GamesResultItemEntity(
    val name : String,
    val released : String,
    val backgroundImage : String,
    val rating : Double,
    @PrimaryKey(autoGenerate = false) val id : Long = 0
)
