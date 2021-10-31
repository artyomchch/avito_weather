package kozlov.artyom.avitoweather.data.network.pojo

data class Temp(
    val day: Double,
    val eve: Int,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)