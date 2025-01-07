package com.devhoseong.weatherapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
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
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable<Screen.Main> { entry ->
            val city = entry.arguments?.let { args ->
                City(
                    id = args.getInt("id", 0),
                    name = args.getString("name", ""),
                    country = args.getString("country", ""),
                    lat = args.getDouble("lat", 0.0),
                    lon = args.getDouble("lon", 0.0)
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