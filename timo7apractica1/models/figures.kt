package com.softim.timo7apractica1.models

abstract class figures(val base: Double, val altura: Double) {

    abstract fun calculararea():Double
    abstract fun calcularperimetro(altura: Double, ancho: Double): Double
}