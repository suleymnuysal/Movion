package com.suleymanuysal.movion.feature_series.presentation.series_screen.view_model

import com.suleymanuysal.movion.feature_series.domain.model.Series
import com.suleymanuysal.movion.feature_series.domain.model.SeriesCategory

data class SeriesState(
    val isLoading: Boolean = false,
    val series: Map<SeriesCategory, List<Series>> = emptyMap(),
    val error: String = "error"
)
