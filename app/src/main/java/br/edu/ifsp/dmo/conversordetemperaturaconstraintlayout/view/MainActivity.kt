package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.R
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.CelsiusToFahrenheit
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.CelsiusToKelvin
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.FahrenheitToCelsius
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.FahrenheitToKelvin
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.KelvinToCelsius
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.KelvinToFahrenheit
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.TemperatureConverter


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var converterStrategy: TemperatureConverter
    private lateinit var radioTemperature: String
    private lateinit var buttonTemperature: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener(){
        binding.buttonCelsius.setOnClickListener{
            buttonTemperature = getString(R.string.celsius)
            handleConversion()
        }

        binding.buttonFahrenheit.setOnClickListener{
            buttonTemperature = getString(R.string.fahrenheit)
            handleConversion()
        }

        binding.buttonKelvin.setOnClickListener{
            buttonTemperature = getString(R.string.kelvin)
            handleConversion()
        }

        binding.radiogroupUnits.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.radio_celsius){
                radioTemperature = getString(R.string.celsius)
            }

            if(checkedId == R.id.radio_fahrenheit){
                radioTemperature = getString(R.string.fahrenheit)
            }

            if(checkedId == R.id.radio_kelvin){
                radioTemperature = getString(R.string.kelvin)
            }

        }

    }

    private fun readTemperature(): Double{
        return try{
            binding.edittextTemperature.text.toString().toDouble()
        }catch (e: NumberFormatException){
            throw NumberFormatException(getString(R.string.error_popup_notify))
        }
    }

    private fun getStrategy(): TemperatureConverter{

        if(radioTemperature.equals(getString(R.string.celsius))){
            if(buttonTemperature.equals(getString(R.string.fahrenheit))){
                converterStrategy = CelsiusToFahrenheit
            }else if(buttonTemperature.equals(getString(R.string.kelvin))){
                converterStrategy = CelsiusToKelvin
            }else{
                Toast.makeText(this, getString(R.string.error_convert), Toast.LENGTH_SHORT).show()
            }
        }else if(radioTemperature.equals(getString(R.string.fahrenheit))){
            if(buttonTemperature.equals(getString(R.string.celsius))){
                converterStrategy = FahrenheitToCelsius
            }else if(buttonTemperature.equals(getString(R.string.kelvin))){
                converterStrategy = FahrenheitToKelvin
            }else{
                Toast.makeText(this, getString(R.string.error_convert), Toast.LENGTH_SHORT).show()
            }
        }else if(radioTemperature.equals(getString(R.string.kelvin))){
            if(buttonTemperature.equals(getString(R.string.celsius))){
                converterStrategy = KelvinToCelsius
            }else if(buttonTemperature.equals(getString(R.string.fahrenheit))){
                converterStrategy = KelvinToFahrenheit
            }else{
                Toast.makeText(this, getString(R.string.error_convert), Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, getString(R.string.error_convert), Toast.LENGTH_SHORT).show()
        }

        return converterStrategy
    }

    private fun handleConversion(){
        converterStrategy = getStrategy()

        try{
            val inputValue = readTemperature()
            val message: String

            binding.textviewResultNumber.text = String.format("%.2f %s",
                                                    converterStrategy.converter(inputValue),
                                                    converterStrategy.getScale());

            if(converterStrategy is CelsiusToFahrenheit){
                message = getString(R.string.msgCtoF)
            }else if(converterStrategy is CelsiusToKelvin){
                message = getString(R.string.msgCtoK)
            }else if(converterStrategy is FahrenheitToCelsius){
                message = getString(R.string.msgFtoC)
            }else if(converterStrategy is FahrenheitToKelvin){
                message = getString(R.string.msgFtoK)
            }else if(converterStrategy is KelvinToCelsius){
                message = getString(R.string.msgKtoC)
            }else if(converterStrategy is KelvinToFahrenheit){
                message = getString(R.string.msgKtoF)
            }else{
                message = getString(R.string.error_convert)
            }

            binding.textviewResultMsg.text = message

        }catch (e: Exception){
            Toast.makeText(this, getString(R.string.error_popup_notify), Toast.LENGTH_SHORT).show()
            Log.e("APP_DMO", e.stackTraceToString())
        }

    }

}