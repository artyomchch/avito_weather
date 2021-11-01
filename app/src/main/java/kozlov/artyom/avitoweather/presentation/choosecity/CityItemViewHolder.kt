package kozlov.artyom.avitoweather.presentation.choosecity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kozlov.artyom.avitoweather.R

class CityItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.city_name)
}