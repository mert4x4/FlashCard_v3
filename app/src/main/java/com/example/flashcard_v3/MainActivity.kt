package com.example.flashcard_v3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }


    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.navHostFragmentContainerView)
        return super.onSupportNavigateUp() || super.onSupportNavigateUp()
    }
}