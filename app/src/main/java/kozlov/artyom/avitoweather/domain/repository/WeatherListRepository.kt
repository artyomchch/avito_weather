package kozlov.artyom.avitoweather.domain.repository

import androidx.lifecycle.LiveData
import kozlov.artyom.avitoweather.data.network.pojo.Coord
import kozlov.artyom.avitoweather.data.network.pojo.WeatherNetwork
import kozlov.artyom.avitoweather.domain.entity.*

interface WeatherListRepository {

    suspend fun getWeather(latitude: Double, longitude: Double): List<WeatherNetwork>

    fun getWeatherHourly(): List<WeatherHourly>

    fun getWeatherDaily(): List<WeatherDaily>

    fun getWeatherDetails(): WeatherDetails

    fun getWeatherCurrent(city: String): WeatherCurrent

    suspend fun getCoordinatesCity(city: String): Coord

    suspend fun editCityItem(cityItem: CityItem)

    suspend fun addCityItem(cityItem: CityItem)

    suspend fun getCityItem(cityItemId: Int): CityItem

    suspend fun deleteCityItem(cityItem: CityItem)

    suspend fun resetEnableCity(disable: Int, enable: Int)

    fun getCityList(): LiveData<List<CityItem>>
}