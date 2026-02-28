package com.suleymanuysal.movion.feature_detail.presentation.more_detail_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.databinding.CastAndCrewRowBinding
import com.suleymanuysal.movion.feature_detail.domain.model.Credit
import java.lang.Exception

class MoreDetailAdapter(private val creditList: List<Credit>,
                        private val onclick : (Int?, Boolean?) -> Unit)
    : RecyclerView.Adapter<MoreDetailAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = CastAndCrewRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val credit = creditList[position]

        val imageUrl = credit.profile_path

        imageUrl?.let { it
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL+it)
                .into(holder.cardImageView, object : Callback{
                    override fun onSuccess() {
                        onclick(null,true)
                    }

                    override fun onError(e: Exception?) {
                        TODO("Not yet implemented")
                    }

                })

        }

        holder.name.text = credit.name

        holder.cardView.setOnClickListener {
            onclick(credit.id,null)
        }
    }

    override fun getItemCount(): Int {
        return creditList.size
    }

    inner class ItemViewHolder(binding: CastAndCrewRowBinding)
        : RecyclerView.ViewHolder(binding.root){

        val cardView  = binding.castAndCrewCardView
        val cardImageView  = binding.castAndCrewImageView
        val name  = binding.textViewCastName

    }
}