package com.ardinata.service_games.data.webservice.dto

import com.google.gson.annotations.SerializedName

data class GamesResultItemDto(
    @SerializedName("name") val name : String,
    @SerializedName("released") val released : String?,
    @SerializedName("background_image") val backgroundImage : String?,
    @SerializedName("rating") val rating : Double,
    @SerializedName("id") val id : Long
)
