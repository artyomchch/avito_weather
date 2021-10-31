package kozlov.artyom.avitoweather.data.network.pojo

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)