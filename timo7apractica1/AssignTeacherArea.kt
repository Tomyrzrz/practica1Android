package com.softim.timo7apractica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_assign_teacher.*

class AssignTeacherArea : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_teacher)

        btn_register_teacher.setOnClickListener {
            asignarTeacher()
        }

        val carreras = arrayOf("Biotecnologia", "Gastronomia", "Tecnologias de Informacion")
        val adaptadorCarreras = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item, carreras)
        spn_carrers_utom.adapter = adaptadorCarreras
    }

    fun asignarTeacher(){
        val nombre = edt_name_teacher.text.toString()
        val apellidop = edt_lastname_teacher.text.toString()
        val profesion = edt_profession_teacher.text.toString()
        val career = spn_carrers_utom.selectedItem.toString()

        when {
            TextUtils.isEmpty(nombre)
                    ->Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(apellidop)
            ->Toast.makeText(this, "Lastname is empty", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(profesion)
            ->Toast.makeText(this, "Profession is empty", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(career)
            ->Toast.makeText(this, "Career is empty", Toast.LENGTH_SHORT).show()
            else ->{
                val teachers = HashMap<String, Any>()
                teachers["name"] = nombre
                teachers["last_name"] = apellidop
                teachers["profession"] = profesion
                teachers["career"] = career
                val referTeacherA : DatabaseReference = FirebaseDatabase.getInstance()
                    .reference.child("Teachers")
                referTeacherA.push().setValue(teachers).addOnCompleteListener {
                    if (it.isSuccessful) {
                       Toast.makeText(applicationContext, "All correct",
                                Toast.LENGTH_SHORT).show()
                            cleanTextos()
                        }else{
                            Toast.makeText(applicationContext,"Error no connect",
                                Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }

    }

    fun cleanTextos(){
        edt_lastname_teacher.setText("")
        edt_name_teacher.setText("")
        edt_profession_teacher.setText("")
    }

}
