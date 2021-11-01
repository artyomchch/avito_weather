package kozlov.artyom.avitoweather.domain.entity

data class CityItem(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val enable: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}
