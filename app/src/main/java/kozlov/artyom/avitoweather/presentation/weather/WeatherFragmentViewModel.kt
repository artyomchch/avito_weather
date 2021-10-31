package kozlov.artyom.avitoweather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kozlov.artyom.avitoweather.data.WeatherListRepositoryImpl
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly

class WeatherFragmentViewModel : ViewModel() {

    private val repository = WeatherListRepositoryImpl

    private val _hourlyItems = MutableLiveData<List<WeatherHourly>>()
    val hourlyItems: LiveData<List<WeatherHourly>>
        get() = _hourlyItems

    private val _dailyItems = MutableLiveData<List<WeatherDaily>>()
    val dailyItems: LiveData<List<WeatherDaily>>
        get() = _dailyItems


}