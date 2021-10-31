package kozlov.artyom.avitoweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kozlov.artyom.avitoweather.databinding.WeatherActivityBinding

class WeatherActivity : AppCompatActivity() {

    private val binding: WeatherActivityBinding by lazy {
        WeatherActivityBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }


}