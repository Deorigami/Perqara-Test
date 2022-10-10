package com.ardinata.service_games.domain.entity

data class GameDetailsEntity(
    val id : Long,
    val name: String,
    val description: String,
    val released: String,
    val backgroundImage: String,
    val rating: Double,
    val publishers: List<PublisherEntity>,
    val playTime: String
) {
    data class PublisherEntity(
        val gamesCount: Long,
        val id: Long,
        val imageBackground: String,
        val name: String,
        val slug: String
    )

    companion object {
        val DEFAULT = GameDetailsEntity(
            0,
            "",
            "",
            "",
            "",
            0.0,
            emptyList(),
            "",
        )

        fun GameDetailsEntity.toGameListItem() : GamesResultItemEntity = GamesResultItemEntity(
            name, released,backgroundImage, rating, id
        )
    }
}
