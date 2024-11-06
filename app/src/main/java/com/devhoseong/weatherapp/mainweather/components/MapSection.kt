package com.devhoseong.weatherapp.mainweather.components

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.devhoseong.domain.model.City
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker

@SuppressLint("ClickableViewAccessibility")
@Composable
fun MapSection(
    city: City?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var naverMap by remember { mutableStateOf<NaverMap?>(null) }
    val lat = city?.lat ?: 0.0
    val lon = city?.lon ?: 0.0
    val cityName = city?.name.orEmpty()

    val marker = Marker().apply {
        position = LatLng(lat, lon)
        captionText = cityName
    }

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            AndroidView(
                factory = {
                    MapView(context).apply {
                        setOnTouchListener { v, event ->
                            when (event.action) {
                                MotionEvent.ACTION_DOWN,
                                MotionEvent.ACTION_MOVE -> {
                                    v.parent.requestDisallowInterceptTouchEvent(true)
                                    false
                                }
                                MotionEvent.ACTION_UP -> {
                                    v.parent.requestDisallowInterceptTouchEvent(false)
                                    false
                                }
                                else -> false
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )  { view ->
                view.getMapAsync { map ->
                    naverMap = map.apply {
                        marker.map = null
                        moveCamera(
                            CameraUpdate.scrollAndZoomTo(
                                LatLng(lat, lon),
                                12.0
                            )
                        )
                        marker.map = this
                    }
                }
            }
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            mapView.onDestroy()
        }
    }
}