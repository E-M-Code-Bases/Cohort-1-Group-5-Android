package com.dominic.movieswatch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dominic.movieswatch.R
import com.dominic.movieswatch.adapters.MoviePagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomePage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    true
                }

                R.id.navigation_search -> {
                    true
                }

                R.id.navigation_my_library -> {
                    findNavController().navigate(R.id.action_homePage_to_favoritesFragment)


                    true
                }

                else -> false
            }
        }

        view.findViewById<ImageView>(R.id.btn_back_to_main).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)

        val movieAdapter = MoviePagerAdapter(this)
        viewPager.adapter = movieAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Now Playing"
                1 -> "Popular"
                2 -> "Top Rated"
                3 -> "Upcoming"
                4 -> "Favourites"
                else -> ""
            }
        }.attach()

        return view
    }
}
