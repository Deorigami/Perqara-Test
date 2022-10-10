package com.ardinata.service_games.data.webservice.mapper

import com.ardinata.service_games.data.webservice.dto.GamesResultItemDto
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.test.perqara.core.model.Result
import com.ardinata.test.perqara.core.model.ResultDto
import com.ardinata.test.perqara.core.model.ResultDto.Companion.toResult
import javax.inject.Inject

class GameListResultDtoMapper @Inject constructor() {
    operator fun invoke(from : ResultDto<List<GamesResultItemDto>>) : Result<List<GamesResultItemEntity>> {
        return from.toResult(from.data?.map { GamesResultItemEntity(
                name = it.name,
                released = it.released ?: "TBD",
                backgroundImage = it.backgroundImage ?: "",
                rating = it.rating,
                id = it.id,
            ) })
    }
}