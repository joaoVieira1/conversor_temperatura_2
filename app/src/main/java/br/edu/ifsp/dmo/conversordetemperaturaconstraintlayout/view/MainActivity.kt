package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.R
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.CelsiusStrategy
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.FahrenheitStrategy
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.KelvinStrategy
import br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model.TemperatureConverter


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var converterStrategy: TemperatureConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener(){
        binding.buttonCelsius.setOnClickListener{
            handleConversion(CelsiusStrategy)
        }

        binding.buttonFahrenheit.setOnClickListener(View.OnClickListener {
            handleConversion(FahrenheitStrategy)
        })

        binding.buttonKelvin.setOnClickListener(View.OnClickListener {
            handleConversion(KelvinStrategy)
        })

    }

    private fun readTemperature(): Double{
        return try{
            binding.edittextTemperature.text.toString().toDouble()
        }catch (e: NumberFormatException){
            throw NumberFormatException("Input error")
        }
    }

    private fun handleConversion(strategy: TemperatureConverter){
        converterStrategy = strategy

        try{
            val inputValue = readTemperature()

            binding.textviewResultNumber.text = String.format("%.2f %s", converterStrategy.converter(inputValue), converterStrategy.getScale());

            binding.textviewResultMsg.text = if(this.converterStrategy is CelsiusStrategy){
                getString(R.string.msgFtoC)
            }else if(this.converterStrategy is FahrenheitStrategy){
                getString(R.string.msgCtoF)
            }else{
                getString(R.string.msgCtoK)
            }

        }catch (e: Exception){
            Toast.makeText(this, getString(R.string.error_popup_notify), Toast.LENGTH_SHORT).show()
            Log.e("APP_DMO", e.stackTraceToString())
        }

    }

}