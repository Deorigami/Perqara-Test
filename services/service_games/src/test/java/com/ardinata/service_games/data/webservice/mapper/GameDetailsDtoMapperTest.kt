package com.ardinata.service_games.data.webservice.mapper

import com.ardinata.service_games.data.webservice.dto.GameDetailsDto
import com.ardinata.service_games.domain.entity.GameDetailsEntity
import com.ardinata.test.perqara.core.model.Result
import com.ardinata.test.perqara.core.model.ResultDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameDetailsDtoMapperTest {
    lateinit var mapper : GameDetailsDtoMapper

    private val resultDto = GameDetailsDto(
        0,
        "",
        "",
        "",
        "",
        0.0,
        listOf(GameDetailsDto.PublisherDto(
            0,
            0,
            "",
            "",
            ""
        )),
        "",
    )

    private val resultEntity = Result(GameDetailsEntity(
        0,
        "",
        "",
        "",
        "",
        0.0,
        listOf(GameDetailsEntity.PublisherEntity(
            0,
            0,
            "",
            "",
            ""
        )),
        ""
    ))

    @BeforeEach
    fun setup(){
        mapper = GameDetailsDtoMapper()
    }

    @Test
    fun successMappingTest(){
        val expectedResult = mapper.invoke(resultDto)
        assertEquals(expectedResult.data, resultEntity.data)
    }
}