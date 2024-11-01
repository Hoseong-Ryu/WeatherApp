package com.devhoseong.weatherapp.mainweather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devhoseong.domain.model.DailyForecast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DailyForecastSection(
    dailyForecasts: List<DailyForecast>,
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
                text = "5일 예보",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                dailyForecasts.take(5).forEach { forecast ->
                    DailyForecastItem(forecast = forecast)
                }
            }
        }
    }
}

@Composable
private fun DailyForecastItem(
    forecast: DailyForecast,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 날짜
        Text(
            text = forecast.timestamp.toFormattedDate(),
            modifier = Modifier.width(80.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        WeatherIcon(
            iconCode = forecast.weatherIcon,
            modifier = Modifier.size(32.dp),
            contentDescription = null
        )

        // 최저/최고 기온
        Row(
            modifier = Modifier.width(120.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "${forecast.minTemp}°",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "${forecast.maxTemp}°",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

private fun Long.toFormattedDate(): String {
    val date = Date(this * 1000L)
    val formatter = SimpleDateFormat("MM/dd (E)", Locale.getDefault())
    return formatter.format(date)
}