package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

object CelsiusToFahrenheit: TemperatureConverter {

    override fun converter(temperature: Double): Double {
        return (temperature * 1.8) + 32
    }

    override fun getScale(): String {
        return "ºF"
    }


}