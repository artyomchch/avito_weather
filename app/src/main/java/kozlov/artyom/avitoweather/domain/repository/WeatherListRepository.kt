package kozlov.artyom.avitoweather.domain.repository

import kozlov.artyom.avitoweather.domain.entity.WeatherCurrent
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherDetails
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly

interface WeatherListRepository {

    suspend fun getWeatherHourly(): List<WeatherHourly>

    suspend fun getWeatherDaily(): List<WeatherDaily>

    suspend fun getWeatherDetails(): WeatherDetails

    suspend fun getWeatherCurrent(): WeatherCurrent
}