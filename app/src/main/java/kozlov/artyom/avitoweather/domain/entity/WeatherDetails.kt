package kozlov.artyom.avitoweather.domain.entity

data class WeatherDetails(
    val sunrise: Int,
    val sunset: Int,
    val wind_speed: Double,
    val uv: Double,
    val pressure: Int,
    val humidity: Int
)
