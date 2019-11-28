package com.softim.timo7apractica1.models

class Courses {

    private var uid: String = ""
    private var teacher: String = ""
    private var career: String = ""
    private var group: String = ""
    private var subjects: String = ""
    private var name_course = ""

    constructor()

    constructor(carrera: String, grupo: String, profe: String, id: String,
                materias: String, name_course: String){
        this.uid = id
        this.teacher = profe
        this.career = carrera
        this.group = grupo
        this.subjects = materias
        this.name_course = name_course
    }
    fun getNameCourse():String{
        return name_course
    }
    fun setNameCourse(pNcourse: String){
        this.name_course = pNcourse
    }

    fun getUid():String{
        return uid
    }
    fun setUid(pUid: String){
        this.uid = pUid
    }

    fun getCareer():String{
        return career
    }
    fun setCareer(pCarer: String){
        this.career = pCarer
    }
    fun getGroup():String{
        return group
    }

    fun setGroup(pGroup: String){
        this.group = pGroup
    }
    fun getTeacher():String{
        return teacher
    }

    fun setTeacher(pTeacher: String){
        this.teacher = pTeacher
    }

    fun getSubjec():String{
        return subjects
    }

    fun setSubjects(pSubjec: String){
        this.subjects = pSubjec
    }

}