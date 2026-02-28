package com.suleymanuysal.movion.feature_movie.presentation.movie_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.databinding.MainCardViewRowBinding
import com.suleymanuysal.movion.feature_movie.domain.model.Movie

class MoviesAdapter(
    private val movieList: List<Movie>,
    private val onclick : (Int) -> Unit

) : RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

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
        imageUrl?.let {it
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL+it)
                .into(holder.cardImageView)
        }

        holder.cardView.setOnClickListener {
            onclick(movie.id)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ItemViewHolder(binding: MainCardViewRowBinding)
        : RecyclerView.ViewHolder(binding.root){

        val cardView  = binding.homeCardView
        val cardImageView  = binding.imageViewHome


    }
}