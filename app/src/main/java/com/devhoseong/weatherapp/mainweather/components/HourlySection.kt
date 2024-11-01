package com.devhoseong.weatherapp.mainweather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devhoseong.domain.model.HourlyForecast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HourlySection(
    hourlyForecasts: List<HourlyForecast>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "시간별 예보",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(hourlyForecasts.take(16)) { forecast -> // 3시간 간격으로 2일 = 16개
                    HourlyForecastItem(forecast = forecast)
                }
            }
        }
    }
}

@Composable
private fun HourlyForecastItem(
    forecast: HourlyForecast,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = forecast.timestamp.toFormattedTime(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(4.dp))

        WeatherIcon(
            iconCode = forecast.weatherIcon,
            modifier = Modifier.size(32.dp),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${forecast.temperature}°",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun Long.toFormattedTime(): String {
    val date = Date(this * 1000L)
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(date)
}