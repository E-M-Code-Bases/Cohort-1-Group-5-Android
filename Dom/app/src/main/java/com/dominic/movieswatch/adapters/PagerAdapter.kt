package com.dominic.movieswatch.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dominic.movieswatch.ui.fragments.HomePage
import com.dominic.movieswatch.ui.fragments.NowPlaying
import com.dominic.movieswatch.ui.fragments.TopRatedFragment
import com.dominic.movieswatch.ui.fragments.UpcomingMoviesFragment
import com.dominic.movieswatch.ui.fragments.Popular

class MoviePagerAdapter(fragmentActivity: HomePage) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NowPlaying()
            1 -> Popular()
            2 -> TopRatedFragment()
            3 -> UpcomingMoviesFragment()
            else -> Popular()
        }
    }
}