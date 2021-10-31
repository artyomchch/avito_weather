package kozlov.artyom.avitoweather.presentation.weather

import androidx.recyclerview.widget.DiffUtil
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly

class DailyItemDiffCallback: DiffUtil.ItemCallback<WeatherDaily>() {
    override fun areItemsTheSame(oldItem: WeatherDaily, newItem: WeatherDaily): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: WeatherDaily, newItem: WeatherDaily): Boolean {
        return oldItem == newItem
    }

}