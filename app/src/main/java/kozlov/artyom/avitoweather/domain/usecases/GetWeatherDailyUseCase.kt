package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetWeatherDailyUseCase(private val weatherListRepository: WeatherListRepository) {

    suspend operator fun invoke(): List<WeatherDaily> = weatherListRepository.getWeatherDaily()

}