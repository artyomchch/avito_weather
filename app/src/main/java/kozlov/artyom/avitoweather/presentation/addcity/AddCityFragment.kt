package kozlov.artyom.avitoweather.presentation.addcity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kozlov.artyom.avitoweather.R
import kozlov.artyom.avitoweather.databinding.FragmentAddCityBinding
import kozlov.artyom.avitoweather.util.NetworkEnable
import kozlov.artyom.avitoweather.util.OnChangeNavigationListener

class AddCityFragment : Fragment() {

    private var _binding: FragmentAddCityBinding? = null
    private val binding: FragmentAddCityBinding
        get() = _binding ?: throw RuntimeException("FragmentAddCityBinding == null")

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: AddCityFragmentViewModel
    private lateinit var onChangeNavigationListener: OnChangeNavigationListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AddCityFragmentViewModel::class.java]

        if (NetworkEnable.isNetworkAvailable(requireContext())) {
            saveCity()
            binding.entryDesc.text = getString(R.string.entry_description)
        } else
            binding.entryDesc.text = getString(R.string.check_internet)

        getLocationData(requireContext())
        checkForPermission(Manifest.permission.ACCESS_FINE_LOCATION, FINE_LOCATION_REQUEST, false)
        checkForPermission(Manifest.permission.ACCESS_COARSE_LOCATION, COARSE_LOCATION_REQUEST, false)
        setupToolbar()
        addChangeTextListeners()
        observeViewModel()
        geolocationButtonClickListener()


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

    private fun geolocationButtonClickListener() {
        binding.geolocationButton.setOnClickListener {
            checkForPermission(Manifest.permission.ACCESS_FINE_LOCATION, FINE_LOCATION_REQUEST, true)
            checkForPermission(Manifest.permission.ACCESS_COARSE_LOCATION, COARSE_LOCATION_REQUEST, false)

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

        viewModel.errorCityName.observe(viewLifecycleOwner) {
            val notFoundMessage = if (it) {
                getString(R.string.city_not_found)
            } else {
                null
            }
            binding.nameTextField.error = notFoundMessage
        }

        viewModel.stateLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressLoadingCity.visibility = View.VISIBLE
            } else
                binding.progressLoadingCity.visibility = View.GONE
        }
    }

    private fun addChangeTextListeners() {
        binding.editNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
                viewModel.resetErrorCityName()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun saveCity() {
        with(binding) {
            saveButton.setOnClickListener {
                viewModel.addCityItem(editNameField.text?.toString())
                viewModel.validCity.observe(viewLifecycleOwner) {
                    if (it) {
                        onChangeNavigationListener.goToWeatherScreen()
                    }
                }
            }
        }
    }


    private fun checkForPermission(permission: String, requestCode: Int, allow: Boolean) {
        when {
            ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED -> {
                if (allow) {
                    viewModel.getGeolocation()
                    onChangeNavigationListener.goToWeatherScreen()
                }
            }
            shouldShowRequestPermissionRationale(permission) ->
                if (requestCode == FINE_LOCATION_REQUEST)
                    showDialog(permission, requestCode)

            else -> ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "$name ${getString(R.string.permission_refused)}", Toast.LENGTH_SHORT).show()
            }
        }

        when (requestCode) {
            FINE_LOCATION_REQUEST -> innerCheck(FINE_LOCATION_NAME)
            COARSE_LOCATION_REQUEST -> innerCheck(COARSE_LOCATION_NAME)
        }
    }

    private fun showDialog(permission: String, requestCode: Int) {
        val builder = AlertDialog.Builder(requireContext())

        builder.apply {
            setMessage(getString(R.string.dialog_city_warning))
            setTitle(getString(R.string.hello))
            setPositiveButton(getString(R.string.ok)) { _, _ ->
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
            }
            val dialog = builder.create()
            dialog.show()
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
                    Log.d("lat lng", "getLocationData: ${location.latitude} + ${location.longitude}")
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FINE_LOCATION_REQUEST = 101
        private const val COARSE_LOCATION_REQUEST = 102

        private const val FINE_LOCATION_NAME = "fine_location"
        private const val COARSE_LOCATION_NAME = "coarse_location"


    }
}
