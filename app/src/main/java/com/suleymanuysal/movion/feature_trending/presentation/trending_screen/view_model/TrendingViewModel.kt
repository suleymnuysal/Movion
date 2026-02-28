package com.suleymanuysal.movion.feature_trending.presentation.trending_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_trending.domain.use_case.get_all_trends.GetAllTrendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val getAllTrendsUseCase: GetAllTrendsUseCase,

) : ViewModel() {

    private val _trendingMovies = MutableStateFlow(TrendState())
    val trendingMovies: StateFlow<TrendState> = _trendingMovies

    private val _trendingSeries = MutableStateFlow(TrendState())
    val trendingSeries: StateFlow<TrendState> = _trendingSeries

    init {
        getAllTrends()
    }

     fun getAllTrends() {
        viewModelScope.launch {
            getAllTrendsUseCase.executeAllTrends().collect { state ->
                when(state){
                    is Resource.Loading -> {
                        _trendingMovies.value = TrendState(isLoading = true)
                        _trendingSeries.value = TrendState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _trendingMovies.value = TrendState(error = "error")
                        _trendingSeries.value = TrendState(error = "error")
                    }
                    is Resource.Success -> {
                        _trendingMovies.value = TrendState(trends = state.data!!.filter { it.mediaType == "movie" }
                            .sortedByDescending { it.releaseDate })
                        _trendingSeries.value = TrendState(trends = state.data.filter { it.mediaType == "tv" }
                            .sortedByDescending { it.firstAirDate })

                    }
                }
            }
        }
    }


}
