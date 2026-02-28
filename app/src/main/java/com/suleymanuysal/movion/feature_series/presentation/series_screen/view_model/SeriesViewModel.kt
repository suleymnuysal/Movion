package com.suleymanuysal.movion.feature_series.presentation.series_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.core.core_common.Resource
import com.suleymanuysal.movion.feature_series.domain.model.Series
import com.suleymanuysal.movion.feature_series.domain.model.SeriesCategory
import com.suleymanuysal.movion.feature_series.domain.use_case.get_all_tvs.GetAllTvsUseCase
import com.suleymanuysal.movion.feature_trending.domain.use_case.get_all_trends.GetAllTrendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.collections.firstOrNull

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getAllTvsUseCase: GetAllTvsUseCase,
    private val getAllTrendsUseCase: GetAllTrendsUseCase
) : ViewModel(){

    private val _series = MutableStateFlow(SeriesState())
    val series : StateFlow<SeriesState> = _series

    private val _latestSeries = MutableStateFlow<Series>(Series(0,""))
    val latestSeries : StateFlow<Series> = _latestSeries

    init {
        getLatestSeries()

        getAllSeries(
        SeriesCategory.AiringToday, SeriesCategory.Popular,
        SeriesCategory.TopRated, SeriesCategory.OnTheAir,
        SeriesCategory.Adventure, SeriesCategory.Comedy,
        SeriesCategory.Documentary, SeriesCategory.Soap,
        SeriesCategory.Drama, SeriesCategory.Family,
        SeriesCategory.TalkShow, SeriesCategory.Mystery,
        SeriesCategory.Reality, SeriesCategory.Kids,
        SeriesCategory.Science, SeriesCategory.War,
        SeriesCategory.Western, SeriesCategory.Animation,
        )

    }

    fun getAllSeries(vararg category: SeriesCategory){
        category.forEach { category ->
            viewModelScope.launch {
                getAllTvsUseCase.executeGetAllTvs(category).collect { state ->
                    when(state){
                        is Resource.Loading -> {
                            _series.value = _series.value.copy(isLoading = true)
                        }
                        is Resource.Error -> {
                            _series.value= _series.value.copy(isLoading = false,
                                error = state.message ?: "unexpected error"
                            )

                        }
                        is Resource.Success -> {
                            _series.value =_series.value.copy(series = _series.value.series + (category to state.data!!),
                                isLoading = false
                            )

                        }
                    }
                }
            }
        }
    }

    fun getLatestSeries() {
        viewModelScope.launch {
            getAllTrendsUseCase.executeAllTrends().collect { trends ->
                val latest = trends.data?.firstOrNull { it -> it.mediaType == "tv" }
                latest?.let { it
                    _latestSeries.value = Series(it.id,it.posterPath)
                }
            }
        }
    }



}