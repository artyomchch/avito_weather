package kozlov.artyom.avitoweather.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityListDao {

    @Query("SELECT * FROM city_items")
    fun getCityList(): LiveData<List<CityItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCityItem(cityItemDbModel: CityItemDbModel)

    @Query("DELETE FROM city_items WHERE id=:cityItemId")
    suspend fun deleteCityItem(cityItemId: Int)

    @Query("SELECT * FROM city_items WHERE enable=:enable LIMIT 1")
    suspend fun getCityItem(enable: Int): CityItemDbModel

    @Query("UPDATE city_items SET enable= :disable WHERE enable= :enable")
    suspend fun resetEnable(disable: Int, enable: Int)
}