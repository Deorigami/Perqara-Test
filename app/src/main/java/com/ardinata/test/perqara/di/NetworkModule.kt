package com.ardinata.test.perqara.di

import android.content.Context
import com.ardinata.test.perqara.core.interception.AuthorizationInterceptor
import com.chuckerteam.chucker.api.ChuckerCollector

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun profidesOkHTTP(@ApplicationContext context: Context) : OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .addInterceptor(AuthorizationInterceptor())
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.rawg.io/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
