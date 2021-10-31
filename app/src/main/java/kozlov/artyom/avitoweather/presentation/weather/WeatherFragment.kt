package kozlov.artyom.avitoweather.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kozlov.artyom.avitoweather.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentWeatherBinding == null")

    private lateinit var hourlyListAdapter: HourlyListAdapter
    private lateinit var viewModel: WeatherFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WeatherFragmentViewModel::class.java]

        viewModel.hourlyItems.observe(viewLifecycleOwner) {
            hourlyListAdapter.submitList(it)
        }

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        hourlyListAdapter = HourlyListAdapter()
        binding.includeHourlyWeather.hourlyRecycler.adapter = hourlyListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}