package com.dominic.movieswatch.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.dominic.movieswatch.R
import com.dominic.movieswatch.adapters.MoviePagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_home -> {
//                    val intent = Intent(this, HomePage::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.navigation_search -> {
//                    setContentView(R.layout.fragment_search)
//                    true
//                }
//                R.id.navigation_my_library -> {
//                    true
//                }
//                else -> false
//            }
//        }
        findViewById<ImageView>(R.id.btn_back_to_main).setOnClickListener {
            onBackPressed()
        }

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val movieAdapter = MoviePagerAdapter(this)
        viewPager.adapter = movieAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Now Playing"
                1 -> "Popular"
                2 -> "Top Rated"
                3 -> "Upcoming"
                else -> ""
            }
        }.attach()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


    }
}
