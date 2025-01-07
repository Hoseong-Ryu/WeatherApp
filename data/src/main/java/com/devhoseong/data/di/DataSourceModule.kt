package com.devhoseong.data.di

import android.content.Context
import com.devhoseong.data.local.CityDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {
    
    @Provides
    fun provideCityDataSource(
        @ApplicationContext context: Context,
        json: Json
    ): CityDataSource {
        return CityDataSource(context, json)
    }
}