package com.example.bingebuddybca.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bingebuddybca.R

class LifeCycleActivity : AppCompatActivity() {

    companion object{
        const val  TAG = "LifeCycleActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_life_cycle)
        Log.d(TAG,"onCreate: is called")
        Toast.makeText(this, "onCreate is called", Toast.LENGTH_SHORT).show()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: is called")
        Toast.makeText(this, "onStart : is called", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: is called")
        Toast.makeText(this, "onResume: is called", Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: is called")
        Toast.makeText(this, "onPause: is called", Toast.LENGTH_SHORT).show()
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: is called")
        Toast.makeText(this, "onStop: is called", Toast.LENGTH_SHORT).show()
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: is called")
        Toast.makeText(this, "onRestart: is called", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: is called")
        Toast.makeText(this, "onDestroy: is called", Toast.LENGTH_SHORT).show()
    }
}
