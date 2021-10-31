package kozlov.artyom.avitoweather.presentation.weather


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.domain.entity.WeatherHourly

class HourlyListAdapter : ListAdapter<WeatherHourly, HourlyItemViewHolder>(HourlyItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_weather, parent, false)
        return HourlyItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: HourlyItemViewHolder, position: Int) {
        val hourlyItem = getItem(position)
        viewHolder.hourly.text = hourlyItem.hour.toString()
        Glide.with(viewHolder.itemView)
            .load(hourlyItem.picture)
            .centerCrop()
            .into(viewHolder.image)
        viewHolder.temp.text = hourlyItem.temp.toString()

    }

}
