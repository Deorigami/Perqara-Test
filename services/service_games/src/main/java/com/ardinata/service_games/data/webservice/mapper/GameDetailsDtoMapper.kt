package com.ardinata.service_games.data.webservice.mapper

import com.ardinata.service_games.data.webservice.dto.GameDetailsDto
import com.ardinata.service_games.domain.entity.GameDetailsEntity
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class GameDetailsDtoMapper @Inject constructor() {
    operator fun invoke(from : GameDetailsDto) : Result<GameDetailsEntity> {
        val gameDetail = GameDetailsEntity(
            id = from.id,
            name = from.name,
            description = from.description,
            released = from.released,
            backgroundImage = from.backgroundImage,
            rating = from.rating,
            publishers = from.publishers.map {
                GameDetailsEntity.PublisherEntity(
                    it.gamesCount,
                    it.id,
                    it.imageBackground,
                    it.name,
                    it.slug,
                )
            },
            playTime = from.playTime
        )
        return Result(
            data = gameDetail,
            status = 1
        )
    }
}