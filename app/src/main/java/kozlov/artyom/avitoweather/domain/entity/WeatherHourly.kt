package kozlov.artyom.avitoweather.domain.entity

data class WeatherHourly(
    val hour: String,
    val picture: String,
    val temp: Int
)
