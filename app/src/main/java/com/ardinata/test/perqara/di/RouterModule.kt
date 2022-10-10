package com.ardinata.test.perqara.di

import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.test.perqara.router.dashboard.DashboardLandingRouter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RouterModule {

    @Binds
    abstract fun provideDashboardLandingRouter(x: DashboardLandingRouter) : DashboardLandingContract.Router
}