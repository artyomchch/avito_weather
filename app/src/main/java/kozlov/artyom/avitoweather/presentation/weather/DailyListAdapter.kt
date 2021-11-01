package kozlov.artyom.avitoweather.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.domain.entity.WeatherDaily

class DailyListAdapter : ListAdapter<WeatherDaily, DailyItemViewHolder>(DailyItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_weather, parent, false)
        return DailyItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DailyItemViewHolder, position: Int) {
        val dailyItem = getItem(position)
        viewHolder.dailyDay.text = dailyItem.day
        viewHolder.dailyDescription.text = dailyItem.description
        Glide.with(viewHolder.itemView)
            .load(dailyItem.image)
            .centerCrop()
            .into(viewHolder.image)
        viewHolder.temp.text = dailyItem.temp.toString()
    }

}