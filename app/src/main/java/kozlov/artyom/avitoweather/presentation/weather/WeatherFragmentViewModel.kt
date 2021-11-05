package kozlov.artyom.avitoweather.presentation.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kozlov.artyom.avitoweather.data.WeatherListRepositoryImpl
import kozlov.artyom.avitoweather.domain.entity.*
import kozlov.artyom.avitoweather.domain.usecases.*


class WeatherFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherListRepositoryImpl(application)
    private val getWeatherUseCase = GetWeatherUseCase(repository)
    private val getWeatherHourlyUseCase = GetWeatherHourlyUseCase(repository)
    private val getWeatherDailyUseCase = GetWeatherDailyUseCase(repository)
    private val getWeatherDetailsUseCase = GetWeatherDetailsUseCase(repository)
    private val getWeatherCurrentUseCase = GetWeatherCurrentUseCase(repository)
    private val getCityUseCase = GetCityItemUseCase(repository)
    private val getCityListUseCase = GetCityListUseCase(repository)


    private val _itemFromDb = MutableLiveData<CityItem>()

    private val _checkItemFromDb = MutableLiveData<CityItem>()

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

    private val _stateLoading = MutableLiveData<Boolean>()
    val stateLoading: LiveData<Boolean>
        get() = _stateLoading

    var cityItem = getCityListUseCase.invoke()

    private val selectedLiveData = MutableLiveData<List<CityItem>>(emptyList())



    fun initWeather() {

        viewModelScope.launch {
            _checkItemFromDb.value = getCityUseCase.invoke(ENABLE)
            if (_itemFromDb.value != _checkItemFromDb.value) {
                _stateLoading.value = true
                _itemFromDb.value = _checkItemFromDb.value
                getWeatherInformation()
            }
            _stateLoading.value = false
        }
    }

    fun cityNull(){
        cityItem = selectedLiveData
    }

    fun invokeGetItem(){
        cityItem = getCityListUseCase.invoke()
    }


    fun updateWeather() {
        viewModelScope.launch {
            _stateLoading.value = true
            getWeatherInformation()
        }
        _stateLoading.value = false
    }

    private suspend fun getWeatherInformation() {
        getWeatherUseCase.invoke(_itemFromDb.value!!.latitude, _itemFromDb.value!!.longitude)
        _hourlyItems.value = getWeatherHourlyUseCase.invoke()
        _dailyItems.value = getWeatherDailyUseCase.invoke()
        _detailsItem.value = getWeatherDetailsUseCase.invoke()
        _currentItem.value = getWeatherCurrentUseCase.invoke(_itemFromDb.value!!.name)
        _stateLoading.value = false
    }


    companion object {
        private const val ENABLE = 1
    }
}