package com.devhoseong.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CityDTO(
    val id: Int,
    val name: String,
    val country: String,
    val coord: CoordDTO
)

@Serializable
data class CoordDTO(
    val lon: Double,
    val lat: Double
)