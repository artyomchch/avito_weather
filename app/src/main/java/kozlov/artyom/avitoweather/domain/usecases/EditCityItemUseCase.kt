package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.entity.CityItem
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class EditCityItemUseCase(private val weatherListRepository: WeatherListRepository) {

    suspend operator fun invoke(cityItemId: CityItem) {
        weatherListRepository.editCityItem(cityItemId)
    }

}