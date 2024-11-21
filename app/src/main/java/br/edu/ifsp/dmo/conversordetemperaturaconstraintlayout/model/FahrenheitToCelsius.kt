package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

object FahrenheitToCelsius: TemperatureConverter {
    override fun converter(temperature: Double): Double {
        return (temperature - 32) / 1.8
    }

    override fun getScale(): String {
        return "ºC"
    }
}