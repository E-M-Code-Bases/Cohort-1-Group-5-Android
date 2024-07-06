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
import com.dominic.movieswatch.databinding.FragmentUpcomingMoviesBinding
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
