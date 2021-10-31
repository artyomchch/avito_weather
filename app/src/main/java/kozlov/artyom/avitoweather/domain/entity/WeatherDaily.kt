package kozlov.artyom.avitoweather.domain.entity

data class WeatherDaily(
    val day: Int,
    val image: String,
    val temp: Double,
    val date: String
)
