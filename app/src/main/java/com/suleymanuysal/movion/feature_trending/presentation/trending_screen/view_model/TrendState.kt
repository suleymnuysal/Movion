package com.suleymanuysal.movion.feature_trending.presentation.trending_screen.view_model

import com.suleymanuysal.movion.feature_trending.domain.model.Trend

data class TrendState(
    var isLoading : Boolean = false,
    var trends : List<Trend> = emptyList(),
    var error : String = "an unexcepted error accord"
)
