package com.dominic.movieswatch.utils

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding
            .inflate(layoutInflater)
        setContentView(binding.root)
    }

        override fun onSupportNavigateUp(): Boolean {
            navController =
                findNavController(R.id.nav_host_fragment)
            return navController.navigateUp() || super.onSupportNavigateUp()
        }
}
