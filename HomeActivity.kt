package com.example.bingebuddybca.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bingebuddybca.R
import com.example.bingebuddybca.adapter.AllMovieAdapter
import com.example.bingebuddybca.databinding.ActivityHomeBinding
import com.example.bingebuddybca.fragment.HomeFragment
import com.example.bingebuddybca.model.Movies
import com.example.bingebuddybca.util.UtilObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {
    var isFragmentVisible: Boolean = false
    private lateinit var allMovieAdapter: AllMovieAdapter
    var allMovieList = mutableListOf<Movies>()

    private lateinit var homeFragment: HomeFragment
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UtilObject.setUpCustomToolBar(this@HomeActivity)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        homeFragment = HomeFragment()

        loadFragment(homeFragment)
        initRecyclerView()
        searchFilter()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_home, fragment)
        transaction.commit()
    }

    private fun initRecyclerView() {
        allMovieAdapter = AllMovieAdapter(this@HomeActivity, allMovieList)
        binding.rvMovieSearch.layoutManager = LinearLayoutManager(this)
        binding.rvMovieSearch.adapter = allMovieAdapter

        setVisibilityVisibleToFragmentHomeContainer()
        setVisibilityGoneToActivityRvMovie()
    }

    private fun searchFilter() {
        binding.etSearchMovie.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchQuery = s.toString().trim()
                if (searchQuery.isNotEmpty()) {
                    searchMovie(searchQuery)
                } else {
                    allMovieList.clear()
                    allMovieAdapter.notifyDataSetChanged()
                    setVisibilityVisibleToFragmentHomeContainer()
                    setVisibilityGoneToActivityRvMovie()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed here
            }
        })
    }

    private fun searchMovie(searchedText: String) {
        if (searchedText.isNotEmpty()) {
            allMovieList.clear()
            firestore.collection("AllMovie")
                .orderBy("categoryType")
                .startAt(searchedText)
                .endAt(searchedText + "\uf8ff")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val allMovie: Movies = document.toObject(Movies::class.java)
                            allMovieList.add(allMovie)

                            setVisibilityVisibleToActivityRvMovie()
                            setVisibilityGoneToFragmentHomeContainer()

                            allMovieAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(
                            this@HomeActivity,
                            "Movie could not be found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun setVisibilityGoneToFragmentHomeContainer() {
        binding.containerHome.visibility = View.GONE
        isFragmentVisible = true
    }

    private fun setVisibilityVisibleToActivityRvMovie() {
        binding.rvMovieSearch.visibility = View.VISIBLE
        isFragmentVisible = false
    }

    private fun setVisibilityGoneToActivityRvMovie() {
        binding.rvMovieSearch.visibility = View.GONE
        isFragmentVisible = true
    }

    private fun setVisibilityVisibleToFragmentHomeContainer() {
        binding.containerHome.visibility = View.VISIBLE
        isFragmentVisible = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_log_out) {
            auth.signOut()
            Toast.makeText(
                this@HomeActivity,
                "Logged Out",
                Toast.LENGTH_SHORT
            ).show()
            UtilObject.screenNavigation(this@HomeActivity, WelcomeActivity::class.java)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
