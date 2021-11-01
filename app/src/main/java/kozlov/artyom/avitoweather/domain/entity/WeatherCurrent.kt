package kozlov.artyom.avitoweather.domain.entity

data class WeatherCurrent(
    val city: String,
    val temp: Int,
    val description: String,
    val feelLike: Int,
    val time: String,
)
