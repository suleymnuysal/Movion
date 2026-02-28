package com.suleymanuysal.movion.feature_series.presentation.series_screen.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.core.core_common.toDetail
import com.suleymanuysal.movion.databinding.FragmentSeriesBinding
import com.suleymanuysal.movion.feature_detail.presentation.detail_screen.view.DetailActivity
import com.suleymanuysal.movion.feature_series.domain.model.SeriesCategory
import com.suleymanuysal.movion.feature_series.presentation.series_screen.view_model.SeriesViewModel
import com.suleymanuysal.movion.feature_series.presentation.series_screen.adapter.SeriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class SeriesFragment : Fragment() {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!
    private val seriesViewModel : SeriesViewModel by viewModels()
    private lateinit var seriesAdapter: SeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeriesBinding.inflate(inflater,container,false)

        initializeRv(binding.onTheAirTvRv,
            binding.airingTodayTvRv,binding.popularTvRv,binding.sciFiAndFantasyTvRv,
            binding.comediesTvRv,binding.soapTvRv,binding.actionAndAdventureTvRv,binding.westernTvRv,
            binding.warPoliticsTvRv,binding.talkShowsTvRv,binding.realityTvRv,binding.mysteryTvRv,
            binding.kidsTvRv,binding.familieTvRv,binding.dramaTvRv,binding.dokumentarfilmTvRv,
            binding.animationTvRv,binding.TopRatedTvRv
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                seriesViewModel.latestSeries.collect { latestSeries ->
                    val imageUrl = latestSeries.poster_path
                    imageUrl?.let { it
                        Picasso.get()
                            .load(Constants.IMAGE_ORIGINAL_URL+it)
                            .into(binding.imageViewLatest)

                    }
                    binding.imageViewLatest.setOnClickListener {
                        toDetail(requireContext(),"tv",latestSeries.id)
                    }
                }

            }
        }

        observeSeries(SeriesCategory.AiringToday,binding.airingTodayTvRv)
        observeSeries(SeriesCategory.OnTheAir,binding.onTheAirTvRv)
        observeSeries(SeriesCategory.Popular,binding.popularTvRv)
        observeSeries(SeriesCategory.TopRated,binding.TopRatedTvRv)
        observeSeries(SeriesCategory.Comedy,binding.comediesTvRv)
        observeSeries(SeriesCategory.Science,binding.sciFiAndFantasyTvRv)
        observeSeries(SeriesCategory.Soap,binding.soapTvRv)
        observeSeries(SeriesCategory.Adventure,binding.actionAndAdventureTvRv)
        observeSeries(SeriesCategory.Western,binding.westernTvRv)
        observeSeries(SeriesCategory.War,binding.warPoliticsTvRv)
        observeSeries(SeriesCategory.TalkShow,binding.talkShowsTvRv)
        observeSeries(SeriesCategory.Reality,binding.realityTvRv)
        observeSeries(SeriesCategory.Mystery,binding.mysteryTvRv)
        observeSeries(SeriesCategory.Kids,binding.kidsTvRv)
        observeSeries(SeriesCategory.Family,binding.familieTvRv)
        observeSeries(SeriesCategory.Drama,binding.dramaTvRv)
        observeSeries(SeriesCategory.Documentary,binding.dokumentarfilmTvRv)
        observeSeries(SeriesCategory.Animation,binding.animationTvRv)

        return binding.root
    }

    private fun initializeRv(vararg rv: RecyclerView){
        rv.forEach { rv ->
            rv.setHasFixedSize(true)
            rv.setItemViewCacheSize(4)
            rv.layoutManager = StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL)
        }
    }

    fun observeSeries(category: SeriesCategory,rv: RecyclerView){

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                seriesViewModel.series.collect { state ->
                    if(!state.isLoading){
                        binding.shimmerForTvShows.visibility = View.GONE
                        binding.fragmentTvShowsContainer.visibility = View.VISIBLE
                    }
                    val series = state.series[category]
                    series?.let { it
                        seriesAdapter = SeriesAdapter(it){seriesId->
                            toDetail(requireContext(),"tv",seriesId)
                        }
                        rv.adapter = seriesAdapter
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}