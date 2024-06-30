package com.example.domflex.ui

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
import com.dominic.movieswatch.databinding.FragmentPopularMoviesBinding
import com.dominic.movieswatch.repository.PopRepo
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.PREF
import com.dominic.movieswatch.viewmodel.PopularProvider
import com.dominic.movieswatch.viewmodel.PopularViewmodel

class Popular : Fragment() {

    // View binding for accessing views in the layout.
    private lateinit var binding: FragmentPopularMoviesBinding

    // Adapter for the RecyclerView to display movie data.
    private lateinit var popAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using view binding.
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)

        // Retrieve the API key from shared preferences.
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val apiKey = sharedPrefs.getString(API_KEY, "")!!

        // Initialize the repository with the API key.
        val popRepo = PopRepo(apiKey)

        // Set up the ViewModel with the repository.
        val popularViewModel: PopularViewmodel by viewModels {
            PopularProvider(popRepo)
        }

        // Initialize the adapter with an empty list and set up item click listener.
        popAdapter = MovieAdapter(emptyList()) { movie ->
            val bundle = Bundle().apply {
                putString("movieTitle", movie.title)  // Pass the selected movie title to the next screen.
            }
            findNavController().navigate(R.id.action_global_movieDetails, bundle)
        }

        // Set up the RecyclerView with a grid layout manager and the adapter.
        binding.recyclerViewpopular.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = popAdapter
        }

        // Observe popular movies from the ViewModel and update the adapter.
        popularViewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            popAdapter.updateMovies(popularMovies)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the adapter to prevent memory leaks.
        binding.recyclerViewpopular.adapter = null
    }
}
