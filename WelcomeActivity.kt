package com.example.bingebuddybca.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bingebuddybca.R
import com.example.bingebuddybca.util.UtilObject
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class WelcomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var signUpBtn : Button  //Declaration
    lateinit var logInBtn : Button  //Declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        auth = Firebase.auth

        //functionality

        signUpBtn = findViewById(R.id.btn_sign_up)
        logInBtn = findViewById(R.id.btn_log_in)

        signUpBtn.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        logInBtn.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, LogInActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser !=null) {
            UtilObject.screenNavigation(this@WelcomeActivity,
                HomeActivity::class.java)
        }
    }
}
