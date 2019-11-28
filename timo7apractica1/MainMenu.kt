package com.softim.timo7apractica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        img_assign_courso_student_menu.setOnClickListener {
            val window_assign = Intent(this,
                AssignCourseStudent::class.java);
            startActivity(window_assign);
        }

        img_assignp_menu.setOnClickListener {
            val window_assignp = Intent(this,
                AssignTeacherArea::class.java);
            startActivity(window_assignp);
        }

        img_create_course_menu.setOnClickListener {
            val window_createc = Intent(this,
                CreateCourses::class.java);
            startActivity(window_createc);
        }

        img_list_courses_menu.setOnClickListener {
            val window_listc = Intent(this,
                ListAllCourses::class.java);
            startActivity(window_listc);
        }

        img_list_data_course_menu.setOnClickListener {
            val window_datac = Intent(this,
                ListDataCourse::class.java);
            startActivity(window_datac);
        }

        img_register_students_menu.setOnClickListener {
            val window_register = Intent(this,
                RegisterStudents::class.java);
            startActivity(window_register);
        }

    }
}
