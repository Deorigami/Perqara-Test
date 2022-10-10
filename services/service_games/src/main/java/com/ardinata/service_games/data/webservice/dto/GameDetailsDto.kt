package com.ardinata.service_games.data.webservice.dto

import com.google.gson.annotations.SerializedName

data class GameDetailsDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("released") val released: String,
    @SerializedName("background_image") val backgroundImage: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("publishers") val publishers: List<PublisherDto>,
    @SerializedName("playtime") val playTime: String
) {
    data class PublisherDto(
        @SerializedName("games_count") val gamesCount: Long,
        @SerializedName("id") val id: Long,
        @SerializedName("image_background") val imageBackground: String,
        @SerializedName("name") val name: String,
        @SerializedName("slug") val slug: String
    )
}
