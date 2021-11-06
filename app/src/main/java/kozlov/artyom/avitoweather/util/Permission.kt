package kozlov.artyom.avitoweather.util

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION

sealed class Permission(vararg val permissions: String) {
    // Individual permissions
    object fineLocation : Permission(ACCESS_FINE_LOCATION)
    object coarseLocation : Permission(ACCESS_COARSE_LOCATION)


}
