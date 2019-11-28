package com.softim.timo7apractica1.models

class cuadrado(base: Double, altura: Double, val lado: Double): figures(base, altura) {

    override fun calculararea(): Double {

        return base*altura
    }

    override fun calcularperimetro(altura: Double, ancho: Double): Double {
        return lado*4
    }



}