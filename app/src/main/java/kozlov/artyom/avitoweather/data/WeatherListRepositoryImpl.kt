package kozlov.artyom.avitoweather.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kozlov.artyom.avitoweather.data.database.AppDatabase
import kozlov.artyom.avitoweather.data.network.RetrofitInstance
import kozlov.artyom.avitoweather.data.network.pojo.Coord
import kozlov.artyom.avitoweather.data.network.pojo.WeatherNetwork
import kozlov.artyom.avitoweather.domain.entity.*
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class WeatherListRepositoryImpl(application: Application) : WeatherListRepository {

    private var weatherInformation = listOf<WeatherNetwork>()
    private val retrofit = RetrofitInstance.api
    private val mapper = WeatherListMapper()
    private val cityListDao = AppDatabase.getInstance(application).carListDao()


    override suspend fun getWeather(latitude: Double, longitude: Double): List<WeatherNetwork> {
        weatherInformation = listOf(retrofit.getPost(lat = latitude, lon = longitude))
        return weatherInformation
    }


    override fun getWeatherHourly(): List<WeatherHourly> {
        return mapper.mapListNetworkModelToListEntityHourly(weatherInformation[0].hourly)
    }

    override fun getWeatherDaily(): List<WeatherDaily> {
        return mapper.mapListNetworkModelToListEntityDaily(weatherInformation[0].daily)
    }

    override fun getWeatherDetails(): WeatherDetails {
        return mapper.mapNetworkModelToEntityDetails(weatherInformation[0].current)
    }

    override fun getWeatherCurrent(city: String): WeatherCurrent {
        return mapper.mapNetworkModelToEntityCurrent(weatherInformation[0].current, city)
    }

    override suspend fun getCoordinatesCity(city: String): Coord {
        return retrofit.getCoordinates(city = city).coord
    }

    override suspend fun editCityItem(cityItem: CityItem) {
        cityListDao.addCityItem(mapper.mapEntityToDbModel(cityItem))
    }

    override suspend fun addCityItem(cityItem: CityItem) {
        cityListDao.addCityItem(mapper.mapEntityToDbModel(cityItem))
    }

    override suspend fun getCityItem(cityItemId: Int): CityItem {
        val dbModel = cityListDao.getCityItem(cityItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun deleteCityItem(cityItem: CityItem) {
        cityListDao.deleteCityItem(cityItem.id)
    }

    override suspend fun resetEnableCity(disable: Int, enable: Int) {
        cityListDao.resetEnable(disable, enable)
    }

    override fun getCityList(): LiveData<List<CityItem>> = Transformations.map(cityListDao.getCityList()) {
        mapper.mapListDbModelToListEntity(it)
    }

}