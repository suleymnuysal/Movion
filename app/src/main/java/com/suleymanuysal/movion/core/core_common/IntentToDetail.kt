package com.suleymanuysal.movion.core.core_common

import android.content.Context
import android.content.Intent
import com.suleymanuysal.movion.feature_detail.presentation.detail_screen.view.DetailActivity

fun toDetail(context: Context, type: String, id: Int){
    val intentToDetail = Intent(context, DetailActivity::class.java)
    intentToDetail.putExtra("type", type)
    intentToDetail.putExtra("id", id)
    context.startActivity(intentToDetail)
}