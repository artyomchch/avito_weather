package kozlov.artyom.avitoweather.presentation.weather

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kozlov.artyom.avitoweather.R

class HourlyItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val hourly: TextView = view.findViewById(R.id.hourly_text)
    val image: ImageView = view.findViewById(R.id.hourly_image)
    val temp: TextView = view.findViewById(R.id.hourly_temp)
}