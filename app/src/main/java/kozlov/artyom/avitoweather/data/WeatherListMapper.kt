package kozlov.artyom.avitoweather.data

import kozlov.artyom.avitoweather.data.network.Constants.PICTURE_TYPE
import kozlov.artyom.avitoweather.data.network.Constants.PICTURE_URL
import kozlov.artyom.avitoweather.data.network.pojo.Current
import kozlov.artyom.avitoweather.data.network.pojo.Daily
import kozlov.artyom.avitoweather.data.network.pojo.Hourly
import kozlov.artyom.avitoweather.domain.entity.WeatherCurrent
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherDetails
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherListMapper {

    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val dateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())

    private fun mapNetworkModelToEntityHourly(hourly: Hourly) = WeatherHourly(
        hour = getTime(hourly.dt),
        picture = PICTURE_URL + hourly.weather[0].icon + PICTURE_TYPE,
        temp = roundOffDecimal(hourly.temp)
    )

    fun mapListNetworkModelToListEntityHourly(list: List<Hourly>) = list.map {
        mapNetworkModelToEntityHourly(it)
    }

    private fun mapNetworkModelToEntityDaily(daily: Daily) = WeatherDaily(
        day = getDate(daily.dt).take(6),
        description = daily.weather[0].description.replace(' ', '\n'),
        image = PICTURE_URL + daily.weather[0].icon + PICTURE_TYPE,
        temp = roundOffDecimal(daily.temp.day)
    )

    fun mapListNetworkModelToListEntityDaily(list: List<Daily>) = list.map {
        mapNetworkModelToEntityDaily(it)
    }

    fun mapNetworkModelToEntityDetails(current: Current) = WeatherDetails(
        sunrise = getTime(current.sunrise),
        sunset = getTime(current.sunset),
        wind_speed = current.wind_speed,
        uv = current.uvi,
        pressure = current.pressure,
        humidity = current.humidity
    )


    fun mapNetworkModelToEntityCurrent(current: Current) = WeatherCurrent(
        city = "Moscow",
        temp = roundOffDecimal(current.temp),
        description = current.weather[0].description,
        feelLike = roundOffDecimal(current.feels_like),
        time = getTime(current.dt)
    )


    private fun getTime(timestamp: Int): String {
        timeFormat.timeZone = TimeZone.getTimeZone("GMT+3:00")
        return timeFormat.format(timestamp * 1000L)
    }

    private fun getDate(timestamp: Int): String {
        dateFormat.timeZone = TimeZone.getTimeZone("GMT+3:00")
        return dateFormat.format(timestamp * 1000L)
    }

    private fun roundOffDecimal(number: Double): Int {
        return number.roundToInt()
    }


}