package kozlov.artyom.avitoweather.domain.entity

data class WeatherDaily(
    val day: String,
    val description: String,
    val image: String,
    val temp: Double
)
