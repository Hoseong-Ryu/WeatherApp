package com.devhoseong.data.di

import android.content.Context
import com.devhoseong.data.local.CityDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    
    @Provides
    @Singleton
    fun provideCityDataSource(
        @ApplicationContext context: Context,
        json: Json
    ): CityDataSource {
        return CityDataSource(context, json)
    }
}