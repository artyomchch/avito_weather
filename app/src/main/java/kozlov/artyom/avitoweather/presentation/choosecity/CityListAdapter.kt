package kozlov.artyom.avitoweather.presentation.choosecity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.domain.entity.CityItem

class CityListAdapter: ListAdapter<CityItem, CityItemViewHolder>(CityItemDiffCallback()) {

    var onCityItemClickListener: ((CityItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_city
            VIEW_TYPE_ENABLED -> R.layout.item_city_enable
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CityItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CityItemViewHolder, position: Int) {
        val cityItem = getItem(position)
        viewHolder.name.text = cityItem.name

        viewHolder.itemView.setOnClickListener {
            onCityItemClickListener?.invoke(cityItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enable) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }
}