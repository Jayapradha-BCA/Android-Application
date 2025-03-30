package com.example.bingebuddybca.activities

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bingebuddybca.R
import com.example.bingebuddybca.databinding.ActivitySignUpBinding
import com.example.bingebuddybca.util.UtilObject
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth   //declaration
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateUpOfViews()
        auth = Firebase.auth

        // Initialize Firebase Auth
        auth = Firebase.auth


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun navigateUpOfViews() {

        binding.btnSignUp.setOnClickListener {
            signUpValidation()
        }
    }

    private fun signUpValidation() {
        val userName = binding.etSignUpName.editText?.text.toString().trim()
        val userEmail = binding.etSignUpEmail.editText?.text.toString().trim()
        val userPassword = binding.etSignUpPassword.editText?.text.toString().trim()



        if (userName.isEmpty() && userEmail.isEmpty() && userPassword.isEmpty()) {

            Toast.makeText(
                this,
                "Please fill all the details.",
                Toast.LENGTH_LONG
            ).show()
        } else if (userName.isEmpty()) {
            Toast.makeText(
                this,
                "Please enter the name.",
                Toast.LENGTH_LONG
            ).show()
        } else if (userEmail.isEmpty()) {
            Toast.makeText(
                this,
                "Please enter the email.",
                Toast.LENGTH_LONG
            ).show()
        } else if (userPassword.isEmpty()) {
            Toast.makeText(
                this,
                "Please enter the Password.",
                Toast.LENGTH_LONG
            ).show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            Toast.makeText(
                this,
                "Please enter valid email.",
                Toast.LENGTH_LONG
            ).show()
        } else if (userPassword.length < 6) {
            Toast.makeText(
                this,
                "Please enter at least 6 character.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            baseContext,
                            "Registered Successfully.",
                            Toast.LENGTH_LONG
                        ).show()

                        UtilObject.screenNavigation(
                            this@SignUpActivity,
                            HomeActivity::class.java
                        )

                    } else {
                        //If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}


