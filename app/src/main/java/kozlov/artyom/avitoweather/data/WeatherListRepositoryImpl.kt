package kozlov.artyom.avitoweather.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kozlov.artyom.avitoweather.data.database.AppDatabase
import kozlov.artyom.avitoweather.data.network.RetrofitInstance
import kozlov.artyom.avitoweather.data.network.pojo.Coord
import kozlov.artyom.avitoweather.data.network.pojo.Coordinates
import kozlov.artyom.avitoweather.data.network.pojo.WeatherNetwork
import kozlov.artyom.avitoweather.domain.entity.*
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

class WeatherListRepositoryImpl(application: Application) : WeatherListRepository {

    private var weatherInformation = listOf<WeatherNetwork>()
    private val retrofit = RetrofitInstance.api
    private val mapper = WeatherListMapper()
    private val cityListDao = AppDatabase.getInstance(application).carListDao()

    init {
        CoroutineScope(Job()).launch {
            Log.d("TAG", "getCoordinatesCity: ${retrofit.getCoordinates(city = "Moscow").coord}")
        }
    }


    override suspend fun getWeatherHourly(): List<WeatherHourly> {
        weatherInformation = listOf(retrofit.getPost())
        return mapper.mapListNetworkModelToListEntityHourly(weatherInformation[0].hourly)
    }

    override suspend fun getWeatherDaily(): List<WeatherDaily> {
        return mapper.mapListNetworkModelToListEntityDaily(weatherInformation[0].daily)
    }

    override suspend fun getWeatherDetails(): WeatherDetails {
        return mapper.mapNetworkModelToEntityDetails(weatherInformation[0].current)
    }

    override suspend fun getWeatherCurrent(): WeatherCurrent {
        return mapper.mapNetworkModelToEntityCurrent(weatherInformation[0].current)
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

    override fun getCityList(): LiveData<List<CityItem>> = Transformations.map(cityListDao.getCityList()) {
        mapper.mapListDbModelToListEntity(it)
    }

}