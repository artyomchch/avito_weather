package kozlov.artyom.avitoweather.presentation.addcity

import android.app.Application
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kozlov.artyom.avitoweather.data.WeatherListRepositoryImpl
import kozlov.artyom.avitoweather.domain.entity.CityItem
import kozlov.artyom.avitoweather.domain.usecases.AddCityItemUseCase
import kozlov.artyom.avitoweather.domain.usecases.GetCoordinatesCityUseCase
import kozlov.artyom.avitoweather.domain.usecases.ResetStateCityUseCase
import java.io.IOException
import java.util.*


class AddCityFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var lat = 0.0
    var lng = 0.0

    private val repository = WeatherListRepositoryImpl(application)
    private val addCityItemUseCase = AddCityItemUseCase(repository)
    private val getCoordinatesCityUseCase = GetCoordinatesCityUseCase(repository)
    private val resetStateCityUseCase = ResetStateCityUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun addCityItem(inputName: String?) {
        val name = parseName(inputName)
        val fieldsValid = validateInput(name)
        if (fieldsValid) {
            viewModelScope.launch {
                resetStateCityUseCase.invoke()
                val coordinates = getCoordinatesCityUseCase.invoke(name)
                val carItem = CityItem(name, coordinates.lat, coordinates.lon, true)
                addCityItemUseCase(carItem)
                // finishWork()
            }
        }

    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        return result
    }

    private fun getNameCityFromCoordinates(lat: Double, lng: Double): String {
        val gcd = Geocoder(getApplication(), Locale.getDefault())
        var locality = ""
        var addresses: List<Address>? = null
        try {
            addresses = gcd.getFromLocation(lat, lng, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (addresses != null && addresses.isNotEmpty()) {
            locality = addresses[0].locality
        }
        return locality
    }


    fun getGeolocation() {
        viewModelScope.launch {
            resetStateCityUseCase.invoke()
            val carItem = CityItem(getNameCityFromCoordinates(lat, lng), lat, lng, true)
            addCityItemUseCase(carItem)
            //finnish
        }
    }

}