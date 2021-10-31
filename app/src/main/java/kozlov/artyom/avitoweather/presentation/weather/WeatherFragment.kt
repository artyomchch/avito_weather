package kozlov.artyom.avitoweather.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentWeatherBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)

        setupNavigation()

        return binding.root
    }

    private fun setupNavigation() {
        with(binding.mainFragmentToolbar) {
            with(findNavController()) {
                imageAddCity.setOnClickListener {
                    navigate(R.id.action_weatherFragment_to_addCityFragment)
                }
                imageChooseCity.setOnClickListener {
                    navigate(R.id.action_weatherFragment_to_chooseCityFragment)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}