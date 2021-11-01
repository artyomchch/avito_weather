package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.entity.WeatherHourly
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetWeatherHourlyUseCase(private val weatherListRepository: WeatherListRepository) {

    suspend operator fun invoke(): List<WeatherHourly> = weatherListRepository.getWeatherHourly()
}