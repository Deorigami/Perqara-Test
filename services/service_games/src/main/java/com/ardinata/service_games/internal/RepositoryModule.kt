package com.ardinata.service_games.internal

import com.ardinata.service_games.data.local.repository.DashboardDBRepositoryImpl
import com.ardinata.service_games.data.webservice.repository.DashboardServiceRepositoryImpl
import com.ardinata.service_games.domain.repository.DashboardDBRepository
import com.ardinata.service_games.domain.repository.DashboardServiceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsDashboardRepository(repo : DashboardServiceRepositoryImpl) : DashboardServiceRepository

    @Binds
    abstract fun bindsDashboardDBRepository(repo : DashboardDBRepositoryImpl) : DashboardDBRepository
}