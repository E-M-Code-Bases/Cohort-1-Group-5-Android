package com.dominic.movieswatch.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dominic.movieswatch.R
import com.dominic.movieswatch.adapters.MovieAdapter
import com.dominic.movieswatch.databinding.FragmentNowPlayingBinding
import com.dominic.movieswatch.repository.NowRepo
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.PREF
import com.dominic.movieswatch.viewmodel.NowPlayingProvider
import com.dominic.movieswatch.viewmodel.NowPlayingViewModel

class NowPlaying : Fragment() {
    private lateinit var binding: FragmentNowPlayingBinding
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)

        val apiKey = sharedPrefs.getString(API_KEY, "")!!

        val repo = NowRepo(apiKey, requireContext())

        val nowPlayingViewModel: NowPlayingViewModel by viewModels {
            NowPlayingProvider(repo)

        }
        nowPlayingViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { popularMovies ->
            movieAdapter = MovieAdapter(popularMovies) { movie ->

                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                }
                findNavController().navigate(
                    R.id.action_homePage_to_movieDetailsFragment,
                    bundle
                )
            }
            binding.nowPlayingRecView.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = movieAdapter
            }

        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.nowPlayingRecView.adapter = null
    }


}
