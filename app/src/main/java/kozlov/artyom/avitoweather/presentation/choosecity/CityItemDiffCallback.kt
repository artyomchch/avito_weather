package kozlov.artyom.avitoweather.presentation.choosecity

import androidx.recyclerview.widget.DiffUtil
import kozlov.artyom.avitoweather.domain.entity.CityItem
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly

class CityItemDiffCallback: DiffUtil.ItemCallback<CityItem>() {
    override fun areItemsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
        return oldItem == newItem
    }


}