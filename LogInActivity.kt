package com.example.bingebuddybca.activities

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bingebuddybca.R
import com.example.bingebuddybca.databinding.ActivityLogInBinding
import com.example.bingebuddybca.util.UtilObject
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        navigationOfViews()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun navigationOfViews(){
        binding.btnLogin.setOnClickListener {
            loginValidation()
        }
    }
    private fun loginValidation(){
        val userEmail = binding.etLogInEmail.editText?.text.toString().trim()
        val userPassword = binding.etLogInPassword.editText?.text.toString().trim()

        if (userEmail.isEmpty() && userPassword.isEmpty()){
            Toast.makeText(this,
                "Please enter you email and password",
                Toast.LENGTH_LONG).show()
        }   else if(userEmail.isEmpty()) {
            Toast.makeText(this,
                "Please enter you email",
                Toast.LENGTH_LONG).show()
        }   else if(userPassword.isEmpty()) {
            Toast.makeText(this,
                "Please enter you password",
                Toast.LENGTH_LONG).show()
        }   else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            Toast.makeText(this,
                "Please enter valid email",
                Toast.LENGTH_LONG).show()
        }   else if(userPassword.length < 6) {
            Toast.makeText(this,
                "Please enter at least 6 characters",
                Toast.LENGTH_LONG).show()
        }   else {
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                        UtilObject.screenNavigation(this@LogInActivity, HomeActivity::class.java)
                    } else {
                        val errorMessage = task.exception?.localizedMessage ?: "Authentication failed."
                        Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

}
