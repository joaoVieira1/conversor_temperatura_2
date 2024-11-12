package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

object CelsiusStrategy: TemperatureConverter {

    override fun converter(temperature: Double) = (temperature - 32) / 1.8

    override fun getScale() = "ºC"

}