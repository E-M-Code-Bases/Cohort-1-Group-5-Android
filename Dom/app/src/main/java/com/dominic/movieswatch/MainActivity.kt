package com.dominic.movieswatch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.dominic.movieswatch.ui.Favorite
import com.dominic.movieswatch.ui.Search
import com.dominic.movieswatch.ui.home
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.homeFragment -> selectedFragment = home()
                R.id.searchFragment -> selectedFragment = Search()
                R.id.favoriteFragment -> selectedFragment = Favorite()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragment, selectedFragment)
                    .commit()
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.homeFragment
    }
}