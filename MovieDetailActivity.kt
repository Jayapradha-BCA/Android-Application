package com.example.bingebuddybca.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bingebuddybca.R
import com.example.bingebuddybca.databinding.ActivityMovieDetail1Binding
import com.example.bingebuddybca.model.Movies
import com.example.bingebuddybca.util.UtilObject
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MovieDetail1Activity : AppCompatActivity() {
    var movie: Movies? = null

    lateinit var firestore: FirebaseFirestore
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityMovieDetail1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMovieDetail1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = Firebase.firestore
        firebaseAuth = Firebase.auth



        getDataFromIntent()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun getDataFromIntent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            movie = intent.getParcelableExtra("movieDetails",Movies::class.java)
        } else{
            movie = intent.getParcelableExtra("movieDetails")
        }
        movie?.let {
            getDataFromMovie(it)
        }
    }

    private fun getDataFromMovie(movie: Movies) {
        val drawableResId = resources.getIdentifier(movie.movieDrawableNamel,
            "drawable",packageName)
        if (drawableResId !=0){
            binding.ivMovieImagel.setImageResource(drawableResId)
        }
        val cast1DrawableResId = resources.getIdentifier(movie.cast1Img,
            "drawable",packageName)
        if (cast1DrawableResId !=0){
            binding.cast1.setImageResource(cast1DrawableResId)
        }
        val cast2DrawableResId = resources.getIdentifier(movie.cast2Img,
            "drawable",packageName)
        if (cast2DrawableResId !=0){
            binding.cast2.setImageResource(cast2DrawableResId)
        }
        val cast3DrawableResId = resources.getIdentifier(movie.cast3Img,
            "drawable",packageName)
        if (cast3DrawableResId !=0){
            binding.cast3.setImageResource(cast3DrawableResId)
        }
        val cast4DrawableResId = resources.getIdentifier(movie.cast4Img,
            "drawable",packageName)
        if (cast4DrawableResId !=0){
            binding.cast4.setImageResource(cast4DrawableResId)
        }
        val cast5DrawableResId = resources.getIdentifier(movie.cast5Img,
            "drawable",packageName)
        if (cast5DrawableResId !=0){
            binding.cast5.setImageResource(cast5DrawableResId)
        }
        val cast6DrawableResId = resources.getIdentifier(movie.cast6Img,
            "drawable",packageName)
        if (cast6DrawableResId !=0){
            binding.cast6.setImageResource(cast6DrawableResId)
        }
        val img1DrawableResId = resources.getIdentifier(movie.movieImg1,
            "drawable",packageName)
        if (img1DrawableResId !=0){
            binding.ivImg1.setImageResource(img1DrawableResId)
        }
        val img2DrawableResId = resources.getIdentifier(movie.movieImg2,
            "drawable",packageName)
        if (img2DrawableResId !=0){
            binding.ivImg2.setImageResource(img2DrawableResId)
        }
        val img3DrawableResId = resources.getIdentifier(movie.movieImg3,
            "drawable",packageName)
        if (img3DrawableResId !=0){
            binding.ivImg3.setImageResource(img3DrawableResId)
        }
        val img4DrawableResId = resources.getIdentifier(movie.movieImg4,
            "drawable",packageName)
        if (img4DrawableResId !=0){
            binding.ivImg4.setImageResource(img4DrawableResId)
        }
        val img5DrawableResId = resources.getIdentifier(movie.movieImg5,
            "drawable",packageName)
        if (img5DrawableResId !=0){
            binding.ivImg5.setImageResource(img5DrawableResId)
        }
        val clr0DrawableResId = resources.getIdentifier(movie.clr0,
            "drawable",packageName)
        if (clr0DrawableResId !=0){
            binding.ivClr.setImageResource(clr0DrawableResId)
        }
        val clr1DrawableResId = resources.getIdentifier(movie.clr1,
            "drawable",packageName)
        if (clr1DrawableResId !=0){
            binding.ivClr1.setImageResource(clr1DrawableResId)
        }
        val clr2DrawableResId = resources.getIdentifier(movie.clr2,
            "drawable",packageName)
        if (clr2DrawableResId !=0){
            binding.ivClr2.setImageResource(clr2DrawableResId)
        }
        val clr3DrawableResId = resources.getIdentifier(movie.clr3,
            "drawable",packageName)
        if (clr3DrawableResId !=0){
            binding.ivClr3.setImageResource(clr3DrawableResId)
        }
        val clr4DrawableResId = resources.getIdentifier(movie.clr4,
            "drawable",packageName)
        if (clr4DrawableResId !=0){
            binding.ivClr4.setImageResource(clr4DrawableResId)
        }
        binding.cast1Name.text = movie.cast1Name
        binding.cast2Name.text = movie.cast2Name
        binding.cast3Name.text = movie.cast3Name
        binding.cast4Name.text = movie.cast4Name
        binding.cast5Name.text = movie.cast5Name
        binding.cast6Name.text = movie.cast6Name

        binding.tvMovieName.text = movie.movieName
        binding.tvMovieDescription.text = movie.movieDescription
        binding.tvMovieHr.text = movie.movieHr
        binding.tvMoviePg.text = movie.moviePg
        binding.tvMovieYear.text = movie.movieYear
        binding.tvMovieRating.text = movie.movieRating
        binding.tvMovieDirector.text = movie.movieDirector
        binding.tvMovieWriter.text = movie.movieWriter
        binding.tvMovieGenre.text = movie.movieGenres
        binding.tvViolence.text = movie.violence
        binding.tvNudity.text = movie.nudity
        binding.tvProfanity.text = movie.profanity
        binding.tvAlcohol.text = movie.alcohol
        binding.tvFrightening.text = movie.frightening
        binding.tvSummary.text = movie.summary
        binding.tvTagLine.text = movie.tagline
        binding.tvReleaseDate.text = movie.date
        binding.tvLanguage.text = movie.language
        binding.tvOrigin.text = movie.origin
        binding.tvOfficial.text = movie.official
        binding.tvCompany.text = movie.company
    }
}
