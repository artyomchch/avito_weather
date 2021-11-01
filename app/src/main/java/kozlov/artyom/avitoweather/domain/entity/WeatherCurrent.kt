package kozlov.artyom.avitoweather.domain.entity

data class WeatherCurrent(
    val city: String,
    val temp: Double,
    val description: String,
    val feelLike: Double,
    val time: String,
)
