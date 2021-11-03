package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.entity.WeatherDetails
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetWeatherDetailsUseCase(private val weatherListRepository: WeatherListRepository) {

    operator fun invoke(): WeatherDetails = weatherListRepository.getWeatherDetails()
}