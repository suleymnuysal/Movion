package com.suleymanuysal.movion.feature_trending.presentation.trending_screen.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.suleymanuysal.movion.core.core_common.toDetail
import com.suleymanuysal.movion.databinding.FragmentTrendingBinding
import com.suleymanuysal.movion.feature_trending.presentation.trending_screen.adapter.TrendingAdapter
import com.suleymanuysal.movion.feature_trending.presentation.trending_screen.view_model.TrendState
import com.suleymanuysal.movion.feature_trending.presentation.trending_screen.view_model.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private var _binding: FragmentTrendingBinding? = null
    private val binding get() = _binding!!
    private val trendingViewModel : TrendingViewModel by viewModels()
    private lateinit var trendingAdapter: TrendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrendingBinding.inflate(inflater,container,false)

        initializeRv(binding.recyclerViewComingSoon,
                           binding.recyclerViewComingTvs
        )

        observeTrends(trendingViewModel.trendingMovies, binding.recyclerViewComingSoon)
        observeTrends(trendingViewModel.trendingSeries, binding.recyclerViewComingTvs)


        return binding.root
    }

    fun observeTrends(type: StateFlow<TrendState>, rv : RecyclerView){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                type.collect { state ->
                    if (!state.isLoading) {
                        binding.shimmerForNewAndHot.visibility = View.GONE
                        binding.newAndHotContainer.visibility = View.VISIBLE
                    }
                    trendingAdapter = TrendingAdapter(state.trends) { type, id ->
                        toDetail(requireContext(), type, id)
                    }
                    rv.adapter = trendingAdapter

                }
            }
        }
    }

    private fun initializeRv(vararg rv: RecyclerView){
        rv.forEach { rv ->
            rv.setHasFixedSize(true)
            rv.setItemViewCacheSize(4)
            rv.layoutManager = StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}