package kozlov.artyom.avitoweather.domain.usecases

import kozlov.artyom.avitoweather.data.network.pojo.Coord
import kozlov.artyom.avitoweather.data.network.pojo.Coordinates
import kozlov.artyom.avitoweather.domain.repository.WeatherListRepository

class GetCoordinatesCityUseCase(private val weatherListRepository: WeatherListRepository) {

    suspend operator fun invoke(city: String): Coord = weatherListRepository.getCoordinatesCity(city)

}