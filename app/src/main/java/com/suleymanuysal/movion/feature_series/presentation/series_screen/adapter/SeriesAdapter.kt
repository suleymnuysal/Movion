package com.suleymanuysal.movion.feature_series.presentation.series_screen.adapter

import com.suleymanuysal.movion.feature_series.domain.model.Series
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.databinding.MainCardViewRowBinding

class SeriesAdapter(private val seriesList: List<Series>,
                    private val onclick : (Int) -> Unit)
    : RecyclerView.Adapter<SeriesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = MainCardViewRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val series = seriesList[position]

        val imageUrl = series.poster_path
        imageUrl?.let {
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL+imageUrl)
                .into(holder.cardImageView)

        }
        holder.cardView.setOnClickListener {
            onclick(series.id)
        }
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

     class ItemViewHolder(binding: MainCardViewRowBinding)
        : RecyclerView.ViewHolder(binding.root){

        val cardView  = binding.homeCardView
        val cardImageView  = binding.imageViewHome

    }
}