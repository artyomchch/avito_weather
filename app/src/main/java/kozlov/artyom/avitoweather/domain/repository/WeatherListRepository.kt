package kozlov.artyom.avitoweather.domain.repository

import androidx.lifecycle.LiveData
import kozlov.artyom.avitoweather.data.network.pojo.Coord
import kozlov.artyom.avitoweather.domain.entity.*

interface WeatherListRepository {

    suspend fun getWeatherHourly(): List<WeatherHourly>

    suspend fun getWeatherDaily(): List<WeatherDaily>

    suspend fun getWeatherDetails(): WeatherDetails

    suspend fun getWeatherCurrent(): WeatherCurrent

    suspend fun getCoordinatesCity(city: String): Coord

    suspend fun editCityItem(cityItem: CityItem)

    suspend fun addCityItem(cityItem: CityItem)

    suspend fun getCityItem(cityItemId: Int): CityItem

    suspend fun deleteCityItem(cityItem: CityItem)

    fun getCityList(): LiveData<List<CityItem>>
}