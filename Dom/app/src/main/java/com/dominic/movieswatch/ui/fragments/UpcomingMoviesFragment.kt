package com.dominic.movieswatch.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dominic.movieswatch.R
import com.dominic.movieswatch.adapters.MovieAdapter
import com.dominic.movieswatch.databinding.FragmentUpcomingMoviesBinding
import com.dominic.movieswatch.model.Movie
import com.dominic.movieswatch.repository.UpcomingRepo
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.PREF
import com.dominic.movieswatch.viewmodel.UpcomingMoviesProvider
import com.dominic.movieswatch.viewmodel.UpcomingViewModel

class UpcomingMoviesFragment : Fragment() {
    private lateinit var binding: FragmentUpcomingMoviesBinding
    private lateinit var upAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingMoviesBinding.inflate(inflater, container, false)
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val apiKey = sharedPrefs.getString(API_KEY, "")!!
        val repository = UpcomingRepo(apiKey)

        val upcomingViewModel: UpcomingViewModel by viewModels {
            UpcomingMoviesProvider(repository)
        }

        upAdapter = MovieAdapter(emptyList()) { movie ->
            val bundle = Bundle().apply {
                putString("movieTitle", movie.title)
            }
            findNavController().navigate(R.id.action_upcomingMovies_to_movieDetails, bundle)
        }

        binding.upcomingRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = upAdapter
        }

        upcomingViewModel.upcomingMovies.observe(viewLifecycleOwner) { upcomingMovies ->
            upAdapter.updateMovies(upcomingMovies)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.upcomingRecyclerView.adapter = null
    }
}
