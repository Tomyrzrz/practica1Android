package com.softim.timo7apractica1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.softim.timo7apractica1.adapters.Spinner_Teacher
import com.softim.timo7apractica1.models.Courses
import com.softim.timo7apractica1.models.Teachers
import kotlinx.android.synthetic.main.activity_create_courses.*

class CreateCourses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_courses)

        val dbref = FirebaseDatabase.getInstance().getReference("Teachers")
        //val datosSpinTeacher = Spinner_Teacher(dbref)

        val carreras = arrayOf("Biotecnologia", "Gastronomia",
            "Tecnologias de Informacion")
        val adaptadorCarreras = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item, carreras)
        spn_carrers_cc.adapter = adaptadorCarreras

        val groups = arrayOf("1ATIC", "1BTIC", "4ATIC", "7ATIC", "7BTIC",
            "10ATIC")
        val adaptadorGrupos = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item, groups)
        spn_groups_cc.adapter = adaptadorGrupos

        dbref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                val teachers = ArrayList<String>()
                for (data in p0.children){
                    val name_teacher = data.getValue(Teachers::class.java)
                        ?.getName().toString() + " " + data.getValue(Teachers::class.java)?.getLast().toString()
                    teachers.add(name_teacher)
                }
                val adaptTeach = ArrayAdapter<String>(applicationContext,
                    android.R.layout.simple_spinner_dropdown_item, teachers)
                spn_teachers_cc.adapter = adaptTeach
            }

        })

        btn_create_course.setOnClickListener {
            createCourse()
        }

    }

    private fun createCourse() {
        val teacher_v = spn_teachers_cc.selectedItem.toString()
        val subjects_v = show_checkbox()
        val group_v = spn_groups_cc.selectedItem.toString()
        val carrer_v = spn_carrers_cc.selectedItem.toString()
        val name_c = edt_name_course.text.toString()

        when{
            TextUtils.isEmpty(teacher_v)
            -> Toast.makeText(this,"Teacher no selected",
                Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(subjects_v)
            -> Toast.makeText(this,"Subjects no selected",
                Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(group_v)
            -> Toast.makeText(this,"Group no selected",
                Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(carrer_v)
            -> Toast.makeText(this,"Carrer no selected",
                Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(name_c)
            -> Toast.makeText(this,"Name of course is empty",
                Toast.LENGTH_SHORT).show()
            else -> {
                val progessDialog = ProgressDialog(this)
                progessDialog.setTitle("Create Courses")
                progessDialog.setMessage("Please wait... connecting...")
                progessDialog.setCanceledOnTouchOutside(false)
                progessDialog.show()

                //val userId = FirebaseAuth.getInstance().currentUser!!.uid
                val refCourses: DatabaseReference = FirebaseDatabase.getInstance()
                    .reference.child("Courses")

                val coursesMap = HashMap<String, Any>()
                //coursesMap["uid"] = userId
                coursesMap["teacher"] = teacher_v
                coursesMap["career"] = carrer_v
                coursesMap["group"] = group_v
                coursesMap["subjects"] = subjects_v
                coursesMap["name_course"] = name_c

                refCourses.push().setValue(coursesMap)
                    .addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "Data correct and saved",
                            Toast.LENGTH_SHORT).show()
                        progessDialog.dismiss()
                        cleanTextos()
                    }else{
                        Toast.makeText(this, "Error we have a problem!!",
                            Toast.LENGTH_SHORT).show()
                        progessDialog.dismiss()
                        //FirebaseAuth.getInstance().signOut()
                    }
                }
                //TAREA
                //Implementar un LOGIN con FIREBASE usando un email y un password
            }
        }

    }

    private fun cleanTextos() {
        edt_name_course.setText("")
        chb_admon.isChecked = false
        chb_bds.isChecked = false
        chb_design.isChecked = false
        chb_english.isChecked = false
        chb_expression.isChecked = false
        chb_modeling.isChecked = false
        chb_network.isChecked = false
        chb_progra.isChecked = false
    }


    private fun show_checkbox(): String  {
        var subjects_selected = ""
        if (chb_admon.isChecked)
            subjects_selected += chb_admon.text.toString() + ","
        if (chb_bds.isChecked)
            subjects_selected += chb_bds.text.toString() + ","
        if (chb_design.isChecked)
            subjects_selected += chb_design.text.toString() + ","
        if (chb_english.isChecked)
            subjects_selected += chb_english.text.toString() + ","
        if (chb_expression.isChecked)
            subjects_selected += chb_expression.text.toString() + ","
        if (chb_modeling.isChecked)
            subjects_selected += chb_modeling.text.toString() + ","
        if (chb_network.isChecked)
            subjects_selected += chb_network.text.toString() + ","
        if (chb_progra.isChecked)
            subjects_selected += chb_progra.text.toString() + ","
        return subjects_selected
    }



}
