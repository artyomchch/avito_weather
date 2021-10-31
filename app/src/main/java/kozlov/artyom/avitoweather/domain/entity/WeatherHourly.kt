package kozlov.artyom.avitoweather.domain.entity

data class WeatherHourly(
    val hour: Int,
    val picture: String,
    val temp: Double
)
