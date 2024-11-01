package com.devhoseong.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devhoseong.domain.model.City
import com.devhoseong.weatherapp.mainweather.MainWeatherRoute
import com.devhoseong.weatherapp.mainweather.rememberMainWeatherCoordinator
import com.devhoseong.weatherapp.searchcity.SearchCityRoute
import com.devhoseong.weatherapp.searchcity.rememberSearchCityCoordinator

@Composable
fun WeatherNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main(),
    ) {
        composable<Screen.Main> { entry ->
            val city = entry.arguments?.let { args ->
                City(
                    id = args.getInt("id", 1839726),
                    name = args.getString("name", "Asan"),
                    country = args.getString("country", "KR"),
                    lat = args.getDouble("lat", 36.78361),
                    lon = args.getDouble("lon", 127.00417)
                )
            }
            MainWeatherRoute(
                coordinator = rememberMainWeatherCoordinator(
                    navController = navController
                ),
                city = city
            )
        }
        
        composable<Screen.Search> {
            SearchCityRoute(
                coordinator = rememberSearchCityCoordinator(
                    navController = navController
                )
            )
        }
    }
}