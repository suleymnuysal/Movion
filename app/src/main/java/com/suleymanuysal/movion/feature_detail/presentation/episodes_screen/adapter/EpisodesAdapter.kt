package com.suleymanuysal.movion.feature_detail.presentation.episodes_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.databinding.TvEpisodesRowBinding
import com.suleymanuysal.movion.feature_detail.domain.model.Episode
import java.lang.Exception

class EpisodesAdapter (private val episodesList: List<Episode>,
                       private val onclick : (Int?, Boolean?) -> Unit)
    : RecyclerView.Adapter<EpisodesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = TvEpisodesRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val episode = episodesList[position]

        val imageUrl = episode.posterPath

        imageUrl.let {
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL+imageUrl)
                .into(holder.cardImageView, object : Callback{
                    override fun onSuccess() {
                        onclick(null,true)
                    }

                    override fun onError(e: Exception?) {
                        TODO("Not yet implemented")
                    }

                })
        }

        holder.name.text = "${episode.episodeNumber}. ${episode.episodeName}"
        val number : Int? = (episode.runtime as? Double)?.toInt()
        holder.runtime.text = "${number} m"
        holder.overView.text = episode.overview

        holder.cardView.setOnClickListener {
            onclick(episode.id,null)
        }
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }

    inner class ItemViewHolder(binding: TvEpisodesRowBinding)
        : RecyclerView.ViewHolder(binding.root){

        val cardView  = binding.tvEpisodeCardView
        val cardImageView  = binding.tvSeasonImageView
        val name  = binding.textViewTvSeasonsName
        val runtime  = binding.textViewEpisodeRuntime
        val overView  = binding.textViewTvSeasonsOverview

    }
}