package br.edu.ifsp.dmo.conversordetemperaturaconstraintlayout.model

object KelvinStrategy: TemperatureConverter {
    override fun converter(temperature: Double) = temperature + 273

    override fun getScale() = "ºK"
}