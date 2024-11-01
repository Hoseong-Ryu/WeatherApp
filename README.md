# WeatherApp

## 아키텍처
- Clean Architecture
- Multi Module (app, domain, data)
- MVVM + Coordinator 패턴
- Kotlin Flow와 Coroutines 사용

## 사용 기술

- Jetpack Compose
- Hilt (의존성 주입)
- Navigation Compose
- Retrofit2 + Kotlin Serialization
- Naver Maps API

## 설정
프로젝트 실행을 위해 필요한 설정:

OpenWeather API 키 설정 (local.properties)
~~~
WEATHER_API_KEY=your_api_key
~~~
Naver Map Client ID 설정 (local.properties)
~~~
NAVER_MAP_API_KEY=your_client_id
~~~
