package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.data.network.pojo.WeatherNetwork
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetWeatherUseCase(private val weatherListRepository: WeatherListRepository) {

    suspend operator fun invoke(latitude: Double, longitude: Double): List<WeatherNetwork> = weatherListRepository.getWeather(latitude, longitude)

}