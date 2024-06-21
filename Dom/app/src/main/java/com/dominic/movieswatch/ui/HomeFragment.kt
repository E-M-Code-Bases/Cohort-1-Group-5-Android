package com.dominic.movieswatch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dominic.movieswatch.R
import com.dominic.movieswatch.adapters.MoviePagerAdapter

class home : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val adapter = MoviePagerAdapter(requireActivity())
        viewPager.adapter = adapter
    }

}