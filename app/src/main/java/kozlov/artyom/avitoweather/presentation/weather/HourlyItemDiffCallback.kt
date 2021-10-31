package kozlov.artyom.avitoweather.presentation.weather

import androidx.recyclerview.widget.DiffUtil
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly

class HourlyItemDiffCallback: DiffUtil.ItemCallback<WeatherHourly>() {
    override fun areItemsTheSame(oldItem: WeatherHourly, newItem: WeatherHourly): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: WeatherHourly, newItem: WeatherHourly): Boolean {
        return oldItem == newItem
    }
}