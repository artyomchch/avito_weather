package kozlov.artyom.avitoweather.data.network

import kozlov.artyom.avitoweather.data.network.pojo.WeatherNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("onecall")
    suspend fun getPost(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LAT) desc: String = LAT,
        @Query(QUERY_PARAM_LON) sort: String = LON,
        @Query(QUERY_PARAM_UNIT) from: String = UNIT,
        @Query(QUERY_PARAM_LANG) lang: String = LANG,
    ): WeatherNetwork


    companion object {
        private const val QUERY_PARAM_API_KEY = "appid"
        private const val QUERY_PARAM_LAT = "lat"
        private const val QUERY_PARAM_LON = "lon"
        private const val QUERY_PARAM_UNIT = "units"
        private const val QUERY_PARAM_LANG = "lang"



        private const val API_KEY = "ca5b7f2aa1380827e5840e917c9ecd57"
        private const val LAT = "55.76"
        private const val LON = "37.62"
        private const val UNIT = "metric"
        private const val LANG = "en"

    }
}