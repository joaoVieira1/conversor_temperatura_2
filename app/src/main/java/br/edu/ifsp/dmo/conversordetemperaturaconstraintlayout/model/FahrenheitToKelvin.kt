package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

object FahrenheitToKelvin: TemperatureConverter {
    override fun converter(temperature: Double): Double {
        return ((temperature - 32) * 5/9) + 273.15
    }

    override fun getScale(): String {
        return "ºK"
    }
}