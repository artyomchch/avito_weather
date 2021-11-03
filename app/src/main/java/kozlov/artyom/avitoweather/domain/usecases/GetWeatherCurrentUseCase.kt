package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.entity.WeatherCurrent
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetWeatherCurrentUseCase(private val weatherListRepository: WeatherListRepository) {
    operator fun invoke(city: String): WeatherCurrent = weatherListRepository.getWeatherCurrent(city)
}