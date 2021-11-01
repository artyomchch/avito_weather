package kozlov.artyom.avitoweather.data.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherNetwork(
    @SerializedName("alerts")
    @Expose
    val alerts: List<Alert>,
    @SerializedName("current")
    @Expose
    val current: Current,
    @SerializedName("daily")
    @Expose
    val daily: List<Daily>,
    @SerializedName("hourly")
    @Expose
    val hourly: List<Hourly>,
    @SerializedName("lat")
    @Expose
    val lat: Double,
    @SerializedName("lon")
    @Expose
    val lon: Double,
    @SerializedName("minutely")
    @Expose
    val minutely: List<Minutely>,
    @SerializedName("timezone")
    @Expose
    val timezone: String,
    @SerializedName("timezone_offset")
    @Expose
    val timezone_offset: Int
)