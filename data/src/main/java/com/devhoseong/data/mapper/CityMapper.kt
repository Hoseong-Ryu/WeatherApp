package com.devhoseong.data.mapper

import com.devhoseong.data.remote.model.CityDTO
import com.devhoseong.domain.model.City

fun CityDTO.mapToCity(): City {
    return City(
        id = id,
        name = name,
        country = country,
        lat = coord.lat,
        lon = coord.lon
    )
}