package kozlov.artyom.avitoweather.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_items")
data class CityItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val enable: Boolean
)
