package kozlov.artyom.avitoweather.data

import kozlov.artyom.avitoweather.data.network.RetrofitInstance
import kozlov.artyom.avitoweather.data.network.pojo.WeatherNetwork
import kozlov.artyom.avitoweather.domain.entity.WeatherCurrent
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherDetails
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

object WeatherListRepositoryImpl : WeatherListRepository {

    private var weatherInformation = listOf<WeatherNetwork>()
    private val retrofit = RetrofitInstance.api
    private val mapper = WeatherListMapper()



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

}