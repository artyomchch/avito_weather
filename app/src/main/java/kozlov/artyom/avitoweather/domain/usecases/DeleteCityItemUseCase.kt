package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.domain.entity.CityItem
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class DeleteCityItemUseCase(private val weatherListRepository: WeatherListRepository) {
    suspend operator fun invoke(cityItem: CityItem){
        weatherListRepository.deleteCityItem(cityItem)
    }

}