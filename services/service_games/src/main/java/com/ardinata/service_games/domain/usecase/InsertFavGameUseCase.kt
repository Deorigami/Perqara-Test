package com.ardinata.service_games.domain.usecase

import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.service_games.domain.repository.DashboardDBRepository
import com.ardinata.test.perqara.core.base.BaseUseCase
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class InsertFavGameUseCase @Inject constructor(
    private val repo : DashboardDBRepository
) : BaseUseCase<GamesResultItemEntity, Long>(){
    override val default: Long
        get() = 0

    override suspend fun build(param: GamesResultItemEntity): Result<Long> {
        return repo.insertFavGame(param)
    }
}