package kozlov.artyom.avitoweather.presentation.addcity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.databinding.FragmentAddCityBinding

class AddCityFragment : Fragment() {

    private var _binding: FragmentAddCityBinding? = null
    private val binding: FragmentAddCityBinding
        get() = _binding ?: throw RuntimeException("FragmentAddCityBinding == null")

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: AddCityFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AddCityFragmentViewModel::class.java]


        setupToolbar()
        addChangeTextListeners()
        saveCity()
        observeViewModel()
        geolocationButtonClickListener()

        return binding.root
    }

    private fun geolocationButtonClickListener() {
        binding.geolocationButton.setOnClickListener {
            context?.let { getLocationData(it) }
            viewModel.getGeolocation()
        }
    }

    private fun setupToolbar() {
        with(binding.mainFragmentToolbar) {
            toolbarText.text = getString(R.string.add_city)
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.invalid_name)
            } else {
                null
            }
            binding.nameTextField.error = message
        }
    }

    private fun addChangeTextListeners() {
        binding.editNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun saveCity() {
        with(binding){
            saveButton.setOnClickListener {
                viewModel.addCityItem(editNameField.text?.toString())
            }
        }

    }

    private fun getLocationData(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    viewModel.lat = location.latitude
                    viewModel.lng = location.longitude
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
