package com.ardinata.service_games.domain.usecase

import com.ardinata.service_games.domain.entity.GamesQueriesEntity
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.service_games.domain.repository.DashboardServiceRepository
import com.ardinata.test.perqara.core.base.BaseUseCase
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class GamesListUseCase @Inject constructor(
    private val repo: DashboardServiceRepository
) : BaseUseCase<GamesQueriesEntity, List<GamesResultItemEntity>>() {
    override val default: List<GamesResultItemEntity>
        get() = emptyList()

    override suspend fun build(param: GamesQueriesEntity): Result<List<GamesResultItemEntity>> {
        return repo.getGamesList(param)
    }

}