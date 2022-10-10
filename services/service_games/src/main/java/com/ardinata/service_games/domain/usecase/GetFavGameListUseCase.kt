package com.ardinata.service_games.domain.usecase

import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.service_games.domain.repository.DashboardDBRepository
import com.ardinata.test.perqara.core.base.BaseUseCase
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class GetFavGameListUseCase @Inject constructor(
    private val repo : DashboardDBRepository
) : BaseUseCase<Unit, List<GamesResultItemEntity>>(){
    override val default: List<GamesResultItemEntity>
        get() = emptyList()

    override suspend fun build(param: Unit): Result<List<GamesResultItemEntity>> {
        return repo.getFavGameList()
    }
}