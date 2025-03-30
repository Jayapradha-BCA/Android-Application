package com.example.bingebuddybca.util

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.bingebuddybca.R

object UtilObject {

    fun screenNavigation(context: Context, destination: Class<*>) {
        val intent = Intent(context, destination)
        context.startActivity(intent)
    }
    fun screenNavigationWithDataPassing(
        context: Context,
        destination: Class<*>?,
        key: String,
        value: Parcelable
    ) {
        val intent = Intent(context,destination)
        intent.putExtra(key, value)
        context.startActivity(intent)
    }
    fun screenNavigationWithDataPassing(
        context: Context,
        destination: Class<*>,
        key: String,
        value: String
    ) {
        val intent = Intent(context, destination)
        intent.putExtra(key, value)
        context.startActivity(intent)
    }


    fun setUpCustomToolBar(activity: AppCompatActivity) {
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbar_custom)
        activity.setSupportActionBar(toolbar)

        val actionBar = activity.supportActionBar
        actionBar?.apply {
            title = " "
            setDisplayHomeAsUpEnabled(true)
        }

        toolbar.setNavigationOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
            activity.finish()
        }
    }
}
