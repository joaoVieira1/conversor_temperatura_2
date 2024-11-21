package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

object CelsiusToKelvin: TemperatureConverter {
    override fun converter(temperature: Double): Double {
        return temperature + 273.15
    }

    override fun getScale(): String {
        return "ºK"
    }
}