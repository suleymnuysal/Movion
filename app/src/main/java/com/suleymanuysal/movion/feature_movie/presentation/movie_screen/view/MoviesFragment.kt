package com.suleymanuysal.movion.feature_movie.presentation.movie_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.core.core_common.toDetail
import com.suleymanuysal.movion.databinding.FragmentMoviesBinding
import com.suleymanuysal.movion.feature_movie.domain.model.MovieCategory
import com.suleymanuysal.movion.feature_movie.presentation.movie_screen.view_model.MovieViewModel
import com.suleymanuysal.movion.feature_movie.presentation.movie_screen.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel : MovieViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater,container,false)

        initializeRv(binding.recyclerViewWar,
            binding.recyclerViewNowPlaying, binding.recyclerViewPopular,
            binding.recyclerViewAction, binding.recyclerViewAdvanture,
            binding.recyclerViewComedy, binding.recyclerViewHorror,
            binding.recyclerViewThriller, binding.recyclerViewDokumentarfilm,
            binding.recyclerViewDrama, binding.recyclerViewFamilie,
            binding.recyclerViewFantasy, binding.recyclerViewHistorie,
            binding.recyclerViewMystery, binding.recyclerViewUpcoming,
            binding.recyclerViewLove, binding.recyclerViewScienceFiction,
            binding.recyclerViewTVFilm, binding.recyclerViewWar,
            binding.recyclerViewWestern, binding.recyclerViewTopRated
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
               movieViewModel.latestMovie.collect { latestMovie ->
                   val imageUrl = latestMovie.posterPath
                   imageUrl?.let { it
                       Picasso.get()
                           .load(Constants.IMAGE_ORIGINAL_URL+it)
                           .into(binding.imageViewHomeLatest)

                   }

                   binding.imageViewHomeLatest.setOnClickListener {
                       toDetail(requireContext(),"movie",latestMovie.id)
                   }
               }
            }
        }

        observeMovies(MovieCategory.NowPlaying,binding.recyclerViewNowPlaying)
        observeMovies(MovieCategory.Popular,binding.recyclerViewPopular)
        observeMovies(MovieCategory.TopRated,binding.recyclerViewTopRated)
        observeMovies(MovieCategory.Action,binding.recyclerViewAction)
        observeMovies(MovieCategory.Adventure,binding.recyclerViewAdvanture)
        observeMovies(MovieCategory.Comedy,binding.recyclerViewComedy)
        observeMovies(MovieCategory.Thriller,binding.recyclerViewThriller)
        observeMovies(MovieCategory.Documentary,binding.recyclerViewDokumentarfilm)
        observeMovies(MovieCategory.Drama,binding.recyclerViewDrama)
        observeMovies(MovieCategory.Family,binding.recyclerViewFamilie)
        observeMovies(MovieCategory.Fantasy,binding.recyclerViewFantasy)
        observeMovies(MovieCategory.History,binding.recyclerViewHistorie)
        observeMovies(MovieCategory.Horror,binding.recyclerViewHorror)
        observeMovies(MovieCategory.Mystery,binding.recyclerViewMystery)
        observeMovies(MovieCategory.Upcoming,binding.recyclerViewUpcoming)
        observeMovies(MovieCategory.Love,binding.recyclerViewLove)
        observeMovies(MovieCategory.Science,binding.recyclerViewScienceFiction)
        observeMovies(MovieCategory.TvFilm,binding.recyclerViewTVFilm)
        observeMovies(MovieCategory.War,binding.recyclerViewWar)
        observeMovies(MovieCategory.Western,binding.recyclerViewWestern)


        return binding.root
    }

    private fun observeMovies(category: MovieCategory, rv : RecyclerView){

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                movieViewModel.movies.collect { state ->
                    if(!state.isLoading){
                        binding.shimmerForMyListHome.visibility = View.GONE
                        binding.homeFragmentContainer.visibility = View.VISIBLE
                    }
                    val movies = state.movies[category]
                    movies?.let { it ->
                        moviesAdapter = MoviesAdapter(it) { movieId ->
                            toDetail(requireContext(),"movie",movieId)
                        }
                        rv.adapter = moviesAdapter

                    }

                }
            }
        }
    }

    private fun initializeRv(vararg rv: RecyclerView){
        rv.forEach { rv ->
            rv.setHasFixedSize(true)
            rv.setItemViewCacheSize(4)
            rv.layoutManager = StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.HORIZONTAL
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}