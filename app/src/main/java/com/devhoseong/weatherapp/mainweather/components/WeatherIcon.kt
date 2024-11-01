package com.devhoseong.weatherapp.mainweather.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.devhoseong.weatherapp.R

@Composable
fun WeatherIcon(
    iconCode: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(id = WeatherIconUtils.getWeatherIconResId(iconCode)),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

object WeatherIconUtils {
    fun getWeatherIconResId(iconCode: String): Int {
        return when (iconCode) {
            "01d" -> R.drawable.ic_01d  // 맑음 (낮)
            "01n" -> R.drawable.ic_02d  // 맑음 (밤)
            "02d" -> R.drawable.ic_03d  // 구름 조금 (낮)
            "02n" -> R.drawable.ic_04d  // 구름 많음
            "03d", "03n" -> R.drawable.ic_09d  // 비
            "04d", "04n" -> R.drawable.ic_10d  // 소나기
            "09d", "09n" -> R.drawable.ic_11d  // 뇌우
            "10d", "10n" -> R.drawable.ic_13d  // 눈
            else -> R.drawable.ic_01d
        }
    }
}