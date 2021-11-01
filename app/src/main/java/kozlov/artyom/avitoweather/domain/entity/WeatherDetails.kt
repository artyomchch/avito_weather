package kozlov.artyom.avitoweather.domain.entity

data class WeatherDetails(
    val sunrise: String,
    val sunset: String,
    val wind_speed: Double,
    val uv: Double,
    val pressure: Int,
    val humidity: Int
)
