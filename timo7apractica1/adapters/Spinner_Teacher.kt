package com.softim.timo7apractica1.adapters

import com.google.firebase.database.*
import com.softim.timo7apractica1.models.Courses
import com.softim.timo7apractica1.models.Teachers
import java.util.*
import kotlin.collections.ArrayList

class Spinner_Teacher(db: DatabaseReference) {

    var reference: DatabaseReference?= db

    fun refreshData(): ArrayList<String>{
        val teachers = ArrayList<String>()
        reference?.child("Teachers")?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                fillSpinner(p0,teachers)
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        return teachers
    }

    fun fillSpinner(snap: DataSnapshot, maestros: ArrayList<String>){
        maestros.clear()
        for (data in snap.children){
            val name_teacher = data.getValue(Teachers::class.java)?.getName().toString()
            maestros.add(name_teacher)
        }
    }

}