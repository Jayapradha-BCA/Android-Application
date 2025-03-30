package com.example.bingebuddybca.activities

import android.os.Bundle
import android.view.Menu
import android.widget.GridLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bingebuddybca.R
import com.example.bingebuddybca.adapter.AllMovieAdapter
import com.example.bingebuddybca.databinding.ActivityAllMovieBinding
import com.example.bingebuddybca.model.Movies
import com.example.bingebuddybca.util.UtilObject
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class AllMovieActivity : AppCompatActivity() {
    private lateinit var allMovieAdapter: AllMovieAdapter
    var allMovieList = mutableListOf<Movies>()
    private lateinit var firestore: FirebaseFirestore
    lateinit var binding: ActivityAllMovieBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityAllMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryType = intent.getStringExtra("categoryType")
        firestore = Firebase.firestore
        
        UtilObject.setUpCustomToolBar(this@AllMovieActivity)
        
        initRecyclerView()
        getDataFromAllProducts(categoryType)


        
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getDataFromAllProducts(categoryType: String?) {
        if (categoryType.isNullOrEmpty()) {
            firestore.collection("AllMovie")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val allMovie: Movies = document.toObject(Movies::class.java)
                            allMovieList.add(allMovie)
                            allMovieAdapter.notifyItemInserted(document.data.size)
                        }
                    } else{
                        Toast.makeText(this@AllMovieActivity,
                            "Error Something Wrong",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }  else if(categoryType == "Movie") {
            firestore.collection("AllMovie")
                .whereEqualTo("categoryType","Movie")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val allMovie = document.toObject(Movies::class.java)
                            allMovieList.add(allMovie)
                            allMovieAdapter.notifyItemInserted(document.data.size)
                        }
                    }  else{
                        Toast.makeText(this@AllMovieActivity,
                            "Error Something Wrong!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }   else if(categoryType == "Drama") {
            firestore.collection("AllMovie")
                .whereEqualTo("categoryType","Drama")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val allMovie = document.toObject(Movies::class.java)
                            allMovieList.add(allMovie)
                            allMovieAdapter.notifyItemInserted(document.data.size)
                        }
                    }  else{
                        Toast.makeText(this@AllMovieActivity,
                            "Error Something Wrong!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }    else if(categoryType == "Anime") {
            firestore.collection("AllMovie")
                .whereEqualTo("categoryType","Anime")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val allMovie = document.toObject(Movies::class.java)
                            allMovieList.add(allMovie)
                            allMovieAdapter.notifyItemInserted(document.data.size)
                        }
                    }  else{
                        Toast.makeText(this@AllMovieActivity,
                            "Error Something Wrong!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }    else if(categoryType == "TV Show") {
            firestore.collection("AllMovie")
                .whereEqualTo("categoryType","TV Show")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val allMovie = document.toObject(Movies::class.java)
                            allMovieList.add(allMovie)
                            allMovieAdapter.notifyItemInserted(document.data.size)
                        }
                    }  else{
                        Toast.makeText(this@AllMovieActivity,
                            "Error Something Wrong!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun initRecyclerView() {
        allMovieAdapter = AllMovieAdapter(this@AllMovieActivity,allMovieList)
        binding.rvAllMovie.layoutManager = GridLayoutManager(this@AllMovieActivity,2)
        binding.rvAllMovie.adapter = allMovieAdapter
    }


}
