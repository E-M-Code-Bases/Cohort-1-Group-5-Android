package com.dominic.movieswatch.ui.fragments

import TRProvider
import TopRatedViewModel
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
import com.dominic.movieswatch.databinding.FragmentTopRatedBinding
import com.dominic.movieswatch.repository.TopRatedRepository
import com.dominic.movieswatch.utils.API_KEY
import com.dominic.movieswatch.utils.PREF


class TopRatedFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedBinding
    private lateinit var tRAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        val sharedPrefs = requireContext().getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val apiKey = sharedPrefs.getString(API_KEY, "")!!
        val repository = TopRatedRepository(apiKey)
        val topRatedViewModel: TopRatedViewModel by viewModels {
            TRProvider(repository)
        }
        topRatedViewModel.topRatedMovies.observe(viewLifecycleOwner) { topRatedMovies ->
            tRAdapter = MovieAdapter(topRatedMovies) { movie ->
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)

                }
                findNavController().navigate(R.id.action_homePage_to_movieDetailsFragment, bundle)


            }
            binding.recyclerViewTR.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = tRAdapter
            }

        }
        return binding.root
    }
    override fun onDestroyView() {
       super.onDestroyView()
       binding.recyclerViewTR.adapter = null
   }
}
