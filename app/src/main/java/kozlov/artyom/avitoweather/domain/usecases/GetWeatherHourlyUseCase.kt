package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.entity.WeatherHourly
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetWeatherHourlyUseCase(private val weatherListRepository: WeatherListRepository) {

    operator fun invoke(): List<WeatherHourly> = weatherListRepository.getWeatherHourly()
}