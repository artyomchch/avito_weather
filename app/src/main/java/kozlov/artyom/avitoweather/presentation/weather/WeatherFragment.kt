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
    private lateinit var dailyListAdapter: DailyListAdapter
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

        viewModel.dailyItems.observe(viewLifecycleOwner) {
            dailyListAdapter.submitList(it)
        }


        setupDetailsView()
        setupRecyclerView()
        setupCurrentView()

        return binding.root
    }

    private fun setupDetailsView(){
        viewModel.detailsItem.observe(viewLifecycleOwner){
            with(binding.includeDetailsWeather){
                sunriseValue.text = it.sunrise
                sunsetValue.text = it.sunset
                pressureValue.text = it.pressure.toString()
                humidityValue.text = it.humidity.toString()
                uvValue.text = it.uv.toString()
                windSpeedValue.text = it.wind_speed.toString()
            }
        }
    }

    private fun setupCurrentView(){
        viewModel.currentItem.observe(viewLifecycleOwner){
            with(binding.includeCurrentWeather){
                temperature.text = it.temp.toString()
                feelLikeValue.text = it.feelLike.toString()
                descriptionCurrentWeather.text = it.description
                updateTime.text = it.time
            }
        }
    }

    private fun setupRecyclerView() {
        hourlyListAdapter = HourlyListAdapter()
        dailyListAdapter = DailyListAdapter()
        with(binding.includeHourlyWeather.hourlyRecycler){
            adapter = hourlyListAdapter
            isNestedScrollingEnabled = false
        }
        with(binding.includeDailyWeather.dailyRecycler){
            adapter = dailyListAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}