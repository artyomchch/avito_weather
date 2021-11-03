package kozlov.artyom.avitoweather.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
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

        viewModel.invokeGetItem()


        viewModel.hourlyItems.observe(viewLifecycleOwner) {
            hourlyListAdapter.submitList(it)
        }

        viewModel.dailyItems.observe(viewLifecycleOwner) {
            dailyListAdapter.submitList(it)
        }

        setupLoadingView()
        setupDetailsView()
        setupRecyclerView()
        setupCurrentView()
        setupAppBarListener()
        swipeRefreshListener()
        observer()

        return binding.root
    }


    private fun observer() {
        viewModel.cityItem.value
        viewModel.cityItem.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {

            } else {
                viewModel.initWeather()
            }
        }

    }


    private fun swipeRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            viewModel.updateWeather()

        }
    }

    private fun setupAppBarListener() {
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            binding.swipeRefreshLayout.isEnabled = verticalOffset == 0
        })
    }


    private fun setupDetailsView() {
        viewModel.detailsItem.observe(viewLifecycleOwner) {
            with(binding.includeDetailsWeather) {
                sunriseValue.text = it.sunrise
                sunsetValue.text = it.sunset
                pressureValue.text = it.pressure.toString()
                humidityValue.text = it.humidity.toString()
                uvValue.text = it.uv.toString()
                windSpeedValue.text = it.wind_speed.toString()
            }
        }
    }

    private fun setupLoadingView() {
        viewModel.stateLoading.observe(viewLifecycleOwner) {
            with(binding) {
                if (it == true) {
                    includeHourlyWeather.hourlyRecycler.visibility = View.INVISIBLE
                    includeDailyWeather.dailyRecycler.visibility = View.INVISIBLE
                    includeCurrentWeather.updateTime.visibility = View.INVISIBLE
                    includeCurrentWeather.temperature.visibility = View.INVISIBLE
                    includeCurrentWeather.descriptionCurrentWeather.visibility = View.INVISIBLE
                    includeCurrentWeather.feelLikeValue.visibility = View.INVISIBLE
                    includeCurrentWeather.feelLikeCelsius.visibility = View.INVISIBLE
                    includeCurrentWeather.temperatureCelsius.visibility = View.INVISIBLE
                    binding.swipeRefreshLayout.isRefreshing = true
                    collapsingToolbar.title = EMPTY
                    includeDetailsWeather.windSpeedValue.visibility = View.INVISIBLE
                    includeDetailsWeather.humidityValue.visibility = View.INVISIBLE
                    includeDetailsWeather.pressureValue.visibility = View.INVISIBLE
                    includeDetailsWeather.uvValue.visibility = View.INVISIBLE
                    includeDetailsWeather.sunsetValue.visibility = View.INVISIBLE
                    includeDetailsWeather.sunriseValue.visibility = View.INVISIBLE
                } else {
                    includeHourlyWeather.hourlyRecycler.visibility = View.VISIBLE
                    includeDailyWeather.dailyRecycler.visibility = View.VISIBLE
                    includeCurrentWeather.updateTime.visibility = View.VISIBLE
                    includeCurrentWeather.temperature.visibility = View.VISIBLE
                    binding.swipeRefreshLayout.isRefreshing = false
                    includeCurrentWeather.descriptionCurrentWeather.visibility = View.VISIBLE
                    includeCurrentWeather.feelLikeValue.visibility = View.VISIBLE
                    includeDetailsWeather.windSpeedValue.visibility = View.VISIBLE
                    includeCurrentWeather.feelLikeCelsius.visibility = View.VISIBLE
                    includeCurrentWeather.temperatureCelsius.visibility = View.VISIBLE
                    includeDetailsWeather.humidityValue.visibility = View.VISIBLE
                    includeDetailsWeather.pressureValue.visibility = View.VISIBLE
                    includeDetailsWeather.uvValue.visibility = View.VISIBLE
                    includeDetailsWeather.sunsetValue.visibility = View.VISIBLE
                    includeDetailsWeather.sunriseValue.visibility = View.VISIBLE
                }
            }
        }


    }

    private fun setupCurrentView() {
        viewModel.currentItem.observe(viewLifecycleOwner) {
            with(binding.includeCurrentWeather) {
                binding.collapsingToolbar.title = it.city
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
        with(binding.includeHourlyWeather.hourlyRecycler) {
            adapter = hourlyListAdapter
            isNestedScrollingEnabled = false
        }
        with(binding.includeDailyWeather.dailyRecycler) {
            adapter = dailyListAdapter
            isNestedScrollingEnabled = false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.cityNull()
    }

    companion object {
        private const val EMPTY = ""
    }


}