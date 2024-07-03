package com.dominic.movieswatch.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dominic.movieswatch.R
import com.dominic.movieswatch.adapters.MovieAdapter
import com.dominic.movieswatch.databinding.FragmentPopularMoviesBinding
import com.dominic.movieswatch.repository.PopRepo
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.PREF
import com.dominic.movieswatch.viewmodel.PopularProvider
import com.dominic.movieswatch.viewmodel.PopularViewmodel

class Popular : Fragment() {
    private lateinit var binding: FragmentPopularMoviesBinding
    private lateinit var popAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)

        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)

        val apiKey = sharedPrefs.getString(API_KEY, "")!!

        val popRepo = PopRepo(apiKey)

        val popularViewModel: PopularViewmodel by viewModels {
            PopularProvider(popRepo)
        }
        popularViewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            popAdapter= MovieAdapter(popularMovies) { movie ->
                // Handle movie click here
            }

            binding.recyclerViewpopular.apply {

                layoutManager = GridLayoutManager(context, 3)

                adapter = popAdapter
            }
        }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerViewpopular.adapter = null
    }
}