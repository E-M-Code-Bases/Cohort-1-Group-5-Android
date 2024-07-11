package com.dominic.movieswatch.utils

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.dominic.movieswatch.R
import com.dominic.movieswatch.databinding.ActivityMainBinding
import com.dominic.movieswatch.ui.fragments.HomePage

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
                findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
            return navController.navigateUp() || super.onSupportNavigateUp()
        }
}
