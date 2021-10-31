package kozlov.artyom.avitoweather.presentation.addcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.databinding.FragmentAddCityBinding

class AddCityFragment : Fragment() {

    private var _binding: FragmentAddCityBinding? = null
    private val binding: FragmentAddCityBinding
        get() = _binding ?: throw RuntimeException("FragmentAddCityBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)

        setupToolbar()
        setupNavigation()

        return binding.root
    }

    private fun setupToolbar() {
        with(binding.mainFragmentToolbar) {
            toolbarText.text = getString(R.string.add_city)
            imageCheckCity.setImageResource(R.drawable.ic_baseline_arrow_back_24)
            imageAddCity.setImageResource(R.drawable.ic_baseline_add_location_24_purple)
        }
    }

    private fun setupNavigation() {
        with(binding.mainFragmentToolbar) {
            with(findNavController()) {
                imageCheckCity.setOnClickListener {
                    navigate(R.id.action_addCityFragment_to_weatherFragment)
                }
                imageChooseCity.setOnClickListener {
                    navigate(R.id.action_addCityFragment_to_chooseCityFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}