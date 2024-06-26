package com.dominic.movieswatch.ui.fragments

import androidx.fragment.app.Fragment

class MovieDetailsFragment : Fragment() {

    /*  private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loadMovieDetails(movieId)

        viewModel.movieDetails.observe(viewLifecycleOwner, Observer { movie ->
            binding.movie = movie
        })

        viewModel.isFavorite.observe(viewLifecycleOwner, Observer { isFavorite ->
            updateFavoriteIcon(isFavorite)
        })

        binding.favoriteIcon.setOnClickListener {
            viewModel.toggleFavorite()
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val iconRes = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        binding.favoriteIcon.setImageResource(iconRes)
    }
}*/

}