package kozlov.artyom.avitoweather.presentation.choosecity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.databinding.FragmentChooseCityBinding
import kozlov.artyom.avitoweather.util.OnChangeNavigationListener

class ChooseCityFragment : Fragment() {

    private var _binding: FragmentChooseCityBinding? = null
    private val binding: FragmentChooseCityBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseCityBinding == null")

    private lateinit var cityListAdapter: CityListAdapter
    private lateinit var viewModel: ChooseCityFragmentViewModel
    private lateinit var onChangeNavigationListener: OnChangeNavigationListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseCityBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ChooseCityFragmentViewModel::class.java]
        viewModel.cityList.observe(viewLifecycleOwner) {
            cityListAdapter.submitList(it)
        }



        setupRecyclerView()
        setupToolbar()
        setupClickListener()
        setupSwipeListener()


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnChangeNavigationListener) {
            onChangeNavigationListener = context
        } else {
            throw RuntimeException("Activity must implement OnChangeNavigationListener")
        }
    }


    private fun setupRecyclerView() {
        cityListAdapter = CityListAdapter()
        binding.cityRecycler.adapter = cityListAdapter
    }

    private fun setupToolbar() {
        with(binding.mainFragmentToolbar) {
            toolbarText.text = getString(R.string.choose_city)
        }
    }

    private fun setupClickListener() {
        cityListAdapter.onCityItemClickListener = {
            viewModel.changeEnableState(it)
            onChangeNavigationListener.goToWeatherScreen()
        }
    }

    private fun setupSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = cityListAdapter.currentList[viewHolder.layoutPosition]
                viewModel.deleteCityItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.cityRecycler)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}