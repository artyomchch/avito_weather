package kozlov.artyom.avitoweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kozlov.artyom.avitoweather.databinding.WeatherActivityBinding
import kozlov.artyom.avitoweather.util.OnChangeNavigationListener

class WeatherActivity : AppCompatActivity(), OnChangeNavigationListener {


    private val binding: WeatherActivityBinding by lazy {
        WeatherActivityBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavigationMenu.setupWithNavController(navController)
    }

    override fun goToWeatherScreen() {
        navController.navigate(R.id.current_navigation_fragment)
    }

}