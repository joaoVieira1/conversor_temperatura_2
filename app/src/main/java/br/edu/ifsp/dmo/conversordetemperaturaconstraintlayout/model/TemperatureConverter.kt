package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

interface TemperatureConverter {

    fun converter(temperature: Double): Double
    fun getScale(): String

}