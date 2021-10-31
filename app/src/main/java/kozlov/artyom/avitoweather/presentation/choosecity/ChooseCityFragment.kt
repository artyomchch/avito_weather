package kozlov.artyom.avitoweather.presentation.choosecity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.databinding.FragmentChooseCityBinding

class ChooseCityFragment : Fragment() {

    private var _binding: FragmentChooseCityBinding? = null
    private val binding: FragmentChooseCityBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseCityBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseCityBinding.inflate(inflater, container, false)
        setupToolbar()


        return binding.root
    }

    private fun setupToolbar() {
        with(binding.mainFragmentToolbar) {
            toolbarText.text = getString(R.string.choose_city)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}