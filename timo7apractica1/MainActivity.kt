package com.softim.timo7apractica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.softim.timo7apractica1.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            val window2 = Intent(this, MainMenu::class.java)
            startActivity(window2)
            //finish()
        }

        btn_login_google.setOnClickListener {
            val window2 = Intent(this, MainMenu::class.java)
            startActivity(window2)
        }
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null){
            val window2 = Intent(this, MainMenu::class.java)
            startActivity(window2)
            finish()
        }
    }

}
