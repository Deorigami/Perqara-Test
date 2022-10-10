package com.ardinata.service_games.domain.usecase

import com.ardinata.service_games.domain.entity.GameDetailsEntity
import com.ardinata.service_games.domain.repository.DashboardServiceRepository
import com.ardinata.test.perqara.core.base.BaseUseCase
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class GameDetailsUseCase @Inject constructor(
    private val repo: DashboardServiceRepository
) : BaseUseCase<String, GameDetailsEntity>(){
    override val default: GameDetailsEntity
        get() = GameDetailsEntity.DEFAULT

    override suspend fun build(param: String): Result<GameDetailsEntity> {
        return repo.getGameDetails(param)
    }
}