package kozlov.artyom.avitoweather.presentation.weather

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kozlov.artyom.avitoweather.R

class DailyItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val dailyDay: TextView = view.findViewById(R.id.daily_day)
    val dailyDescription: TextView = view.findViewById(R.id.daily_description)
    val image: ImageView = view.findViewById(R.id.daily_image)
    val temp: TextView = view.findViewById(R.id.daily_temp)
}