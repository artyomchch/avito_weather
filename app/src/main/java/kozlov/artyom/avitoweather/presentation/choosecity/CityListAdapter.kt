package kozlov.artyom.avitoweather.presentation.choosecity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.domain.entity.CityItem

class CityListAdapter: ListAdapter<CityItem, CityItemViewHolder>(CityItemDiffCallback()) {

    var onCityItemClickListener: ((CityItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CityItemViewHolder, position: Int) {
        val cityItem = getItem(position)
        viewHolder.name.text = cityItem.name

        viewHolder.itemView.setOnClickListener {

        }
    }
}