package com.softim.timo7apractica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.*
import com.softim.timo7apractica1.models.Courses
import kotlinx.android.synthetic.main.activity_list_data_course.*

class ListDataCourse : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data_course)

        val reference = FirebaseDatabase.getInstance().getReference("Courses")

        reference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val names_courses = ArrayList<String>()
                for (datos in p0.children){
                    val name_c = datos.child("name_course").getValue(String::class.java)
                    names_courses.add(name_c!!)
                }

                val adaptador = ArrayAdapter<String>(applicationContext,
                    android.R.layout.simple_spinner_dropdown_item, names_courses)
                spn_courses_dc.adapter = adaptador
            }
        })

        spn_courses_dc.onItemSelectedListener = this

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        val valor = p0?.selectedItem.toString()
        //Toast.makeText(applicationContext, valor, Toast.LENGTH_LONG).show()
        var courseData = ""

        val dataConsult = FirebaseDatabase.getInstance().reference
            dataConsult.child("Courses")
                .orderByChild("name_course")
                .equalTo(valor)
                .limitToFirst(1)
                .addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(p0: DataSnapshot) {
                        for(dato in p0.children){
                            val courseconsul = dato.getValue(Courses::class.java)
                            courseData += "Course: " + valor + "\n"
                            courseData += "Career: " + courseconsul?.getCareer() + "\n"
                            courseData += "Teacher: " + courseconsul?.getTeacher() + "\n"
                            courseData += "Group: " + courseconsul?.getGroup() + "\n"
                            courseData += "Subjects: " + courseconsul?.getSubjec()
                        }
                        txt_data_of_course.setText(courseData)
                    }

                    override fun onCancelled(p0: DatabaseError) {
                    }
                })
    }



}
