package com.ardinata.service_games.internal

import android.content.Context
import androidx.room.Room
import com.ardinata.service_games.data.local.DashboardDB
import com.ardinata.service_games.data.local.dao.FavGameDao
import com.ardinata.service_games.data.webservice.service.GamesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesRetorfit(retrofit: Retrofit) : GamesService = retrofit.create(GamesService::class.java)

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context) : DashboardDB = Room.databaseBuilder(
        context,
        DashboardDB::class.java,
        DashboardDB.DBName
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesTodoDao(db: DashboardDB) : FavGameDao = db.getFavGameDao()
}