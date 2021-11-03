package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class ResetStateCityUseCase(private val weatherListRepository: WeatherListRepository) {
    suspend operator fun invoke() {
        weatherListRepository.resetEnableCity(0, 1)
    }
}