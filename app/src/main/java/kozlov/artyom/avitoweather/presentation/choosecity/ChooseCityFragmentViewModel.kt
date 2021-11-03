package kozlov.artyom.avitoweather.presentation.choosecity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kozlov.artyom.avitoweather.data.WeatherListRepositoryImpl
import kozlov.artyom.avitoweather.domain.entity.CityItem
import kozlov.artyom.avitoweather.domain.usecases.DeleteCityItemUseCase
import kozlov.artyom.avitoweather.domain.usecases.EditCityItemUseCase
import kozlov.artyom.avitoweather.domain.usecases.GetCityListUseCase
import kozlov.artyom.avitoweather.domain.usecases.ResetStateCityUseCase

class ChooseCityFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherListRepositoryImpl(application)

    private val getCityListUseCase = GetCityListUseCase(repository)
    private val deleteCityItemUseCase = DeleteCityItemUseCase(repository)
    private val editCityItemUseCase = EditCityItemUseCase(repository)
    private val resetStateCityUseCase = ResetStateCityUseCase(repository)

    val cityList = getCityListUseCase.invoke()

    fun changeEnableState(cityItem: CityItem) {

        viewModelScope.launch {
            resetStateCityUseCase.invoke()
            val newItem = cityItem.copy(enable = !cityItem.enable)
            editCityItemUseCase(newItem)
        }
    }

    fun deleteCityItem(cityItem: CityItem) {
        viewModelScope.launch {
            deleteCityItemUseCase(cityItem)
        }
    }

}