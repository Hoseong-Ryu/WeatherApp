package com.devhoseong.data.local

import android.content.Context
import com.devhoseong.data.remote.model.CityDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import javax.inject.Inject

class CityDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json
) {
    private var cities: List<CityDTO> = emptyList()

   private suspend fun getCities(): List<CityDTO> {
       if (cities.isNotEmpty()) {
           return cities
       }

       return withContext(Dispatchers.IO) {
           val fileName = "citylist.json"
           var inputStream: InputStream? = null
           try {
               inputStream = context.assets.open(fileName)
               val jsonString = inputStream.bufferedReader().use { it.readText() }
               cities = json.decodeFromString<List<CityDTO>>(jsonString)
               cities
           } catch (e: Exception) {
               emptyList()
           } finally {
               inputStream?.close()
           }
       }
   }

   suspend fun searchCities(query: String): List<CityDTO> {
       val lowercaseQuery = query.lowercase()
       return getCities()
           .filter {
               it.name.lowercase().contains(lowercaseQuery) ||
                       it.country.lowercase().contains(lowercaseQuery)
           }
   }
}