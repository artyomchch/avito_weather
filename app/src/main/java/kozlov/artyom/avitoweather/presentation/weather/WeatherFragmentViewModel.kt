package kozlov.artyom.avitoweather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kozlov.artyom.avitoweather.data.WeatherListRepositoryImpl
import kozlov.artyom.avitoweather.domain.entity.WeatherCurrent
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherDetails
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly
import kozlov.artyom.avitoweather.domain.usecases.GetWeatherCurrentUseCase
import kozlov.artyom.avitoweather.domain.usecases.GetWeatherDailyUseCase
import kozlov.artyom.avitoweather.domain.usecases.GetWeatherDetailsUseCase
import kozlov.artyom.avitoweather.domain.usecases.GetWeatherHourlyUseCase

class WeatherFragmentViewModel : ViewModel() {

    private val repository = WeatherListRepositoryImpl
    private val getWeatherHourlyUseCase = GetWeatherHourlyUseCase(repository)
    private val getWeatherDailyUseCase = GetWeatherDailyUseCase(repository)
    private val getWeatherDetailsUseCase = GetWeatherDetailsUseCase(repository)
    private val getWeatherCurrentUseCase = GetWeatherCurrentUseCase(repository)

    private val _hourlyItems = MutableLiveData<List<WeatherHourly>>()
    val hourlyItems: LiveData<List<WeatherHourly>>
        get() = _hourlyItems

    private val _dailyItems = MutableLiveData<List<WeatherDaily>>()
    val dailyItems: LiveData<List<WeatherDaily>>
        get() = _dailyItems

    private val _detailsItem = MutableLiveData<WeatherDetails>()
    val detailsItem: LiveData<WeatherDetails>
        get() = _detailsItem

    private val _currentItem = MutableLiveData<WeatherCurrent>()
    val currentItem: LiveData<WeatherCurrent>
        get() = _currentItem


    init {
        viewModelScope.launch {
            _hourlyItems.value = getWeatherHourlyUseCase.invoke()
            _dailyItems.value = getWeatherDailyUseCase.invoke()
            _detailsItem.value = getWeatherDetailsUseCase.invoke()
            _currentItem.value = getWeatherCurrentUseCase.invoke()
        }
    }

}