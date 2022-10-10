package com.ardinata.service_games.domain.usecase

import com.ardinata.service_games.domain.repository.DashboardDBRepository
import com.ardinata.test.perqara.core.base.BaseUseCase
import com.ardinata.test.perqara.core.model.Result
import javax.inject.Inject

class DeleteFavGameListUseCase @Inject constructor(
    private val repo : DashboardDBRepository
) : BaseUseCase<Unit, Int>(){
    override val default: Int
        get() = 0

    override suspend fun build(param: Unit): Result<Int> {
        return repo.deleteFavGameList()
    }
}