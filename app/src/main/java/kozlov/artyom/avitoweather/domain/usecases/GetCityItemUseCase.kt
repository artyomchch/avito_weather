package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetCityItemUseCase(private val weatherListRepository: WeatherListRepository) {
    suspend operator fun invoke(enable: Int) = weatherListRepository.getCityItem(enable)
}