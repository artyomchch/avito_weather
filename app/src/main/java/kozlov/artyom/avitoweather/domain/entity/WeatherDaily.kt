package kozlov.artyom.avitoweather.domain.entity

data class WeatherDaily(
    val day: Int,
    val picture: String,
    val temp: Double,
    val description: String
)
