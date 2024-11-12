package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

object FahrenheitStrategy: TemperatureConverter {
    override fun converter(temperature: Double) = 1.8 * temperature + 32

    override fun getScale(): String = "ºF"
}