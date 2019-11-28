package com.softim.timo7apractica1.models

class Teachers{

    private var name: String = ""
    private var lastname: String = ""
    private var profession: String = ""
    private var career: String = ""

    constructor()

    constructor(name: String, lastname: String, profession: String, career: String)
    {
        this.name = name
        this.lastname = lastname
        this.career = career
        this.profession = profession
    }

    fun getName():String{
        return name
    }
    fun setName(pName: String){
        this.name = pName
    }

    fun getLast():String{
        return lastname
    }
    fun setLast(pLast: String){
        this.lastname = pLast
    }

    fun getCareer():String{
        return career
    }
    fun setCareer(pCarer: String){
        this.career = pCarer
    }
    fun getProfess():String{
        return profession
    }

    fun setProfess(pProfess: String){
        this.profession = pProfess
    }

}