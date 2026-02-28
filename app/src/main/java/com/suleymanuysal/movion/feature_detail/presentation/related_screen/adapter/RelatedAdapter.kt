package com.suleymanuysal.movion.feature_detail.presentation.related_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.databinding.MainCardViewRowBinding
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import java.lang.Exception

class RelatedAdapter(
    private val movieList: List<Movie>,
    private val onclick : (Int?, Boolean?) -> Unit,
) : RecyclerView.Adapter<RelatedAdapter.ItemViewHolder>() {

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
        val movie = movieList[position]

        val imageUrl = movie.posterPath
        imageUrl?.let {
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL+imageUrl)
                .into(holder.cardImageView,object : Callback{
                    override fun onSuccess() {
                        onclick(null,true)
                    }

                    override fun onError(e: Exception?) {
                        TODO("Not yet implemented")
                    }

                })
        }

        holder.cardView.setOnClickListener {
            onclick(movie.id,null)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ItemViewHolder(binding: MainCardViewRowBinding)
        : RecyclerView.ViewHolder(binding.root){

        val cardView  = binding.homeCardView
        val cardImageView  = binding.imageViewHome

    }
}