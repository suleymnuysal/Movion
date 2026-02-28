package com.suleymanuysal.movion.feature_trending.presentation.trending_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.core.core_common.toUIFormatDate
import com.suleymanuysal.movion.databinding.NewAndHotRowBinding
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_series.domain.model.Series
import com.suleymanuysal.movion.feature_trending.domain.model.Trend
import java.util.Date

class TrendingAdapter (
    private val trendLists: List<Trend>,
    private val onclick : (String,Int) -> Unit
) : RecyclerView.Adapter<TrendingAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = NewAndHotRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val trend = trendLists[position]

        holder.overView.text = trend.overview ?: ""

        if (trend.mediaType == "movie") {
            holder.name.text = trend.title ?: ""
            holder.releaseDay.text = trend.releaseDate ?: ""
            holder.releaseDate.text = trend.releaseDate?.toUIFormatDate() ?: ""
            holder.cardViewInfo.setOnClickListener {
                onclick("movie",trend.id)
            }

        }else if (trend.mediaType == "tv"){
            holder.name.text = trend.name ?: ""
            holder.releaseDay.text = trend.firstAirDate ?: ""
            holder.releaseDate.text = trend.firstAirDate?.toUIFormatDate() ?: ""
            holder.cardViewInfo.setOnClickListener {
                onclick("tv",trend.id)
            }
        }

        val imageUrl = trend.posterPath
        imageUrl?.let {
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL+imageUrl)
                .into(holder.imageView)
        }


    }


    override fun getItemCount(): Int {
       return trendLists.size
    }

    inner class ItemViewHolder(binding: NewAndHotRowBinding)
        : RecyclerView.ViewHolder(binding.root){

            val name = binding.upcomingName
            val overView = binding.upcomingOverview
            val releaseDay = binding.upcomingRelaseDay
            val releaseDate = binding.textViewRelaseDate
            val imageView = binding.upcomingImageView
            val cardViewInfo = binding.upcomingInfo


    }
}