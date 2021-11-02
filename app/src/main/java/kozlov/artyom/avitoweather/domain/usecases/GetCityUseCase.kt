package kozlov.artyom.avitoweather.domain.usecases

import androidx.lifecycle.LiveData
import kozlov.artyom.avitoweather.domain.entity.CityItem
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetCityUseCase (private val weatherListRepository: WeatherListRepository) {
    operator fun invoke(): LiveData<List<CityItem>> = weatherListRepository.getCityList()
}