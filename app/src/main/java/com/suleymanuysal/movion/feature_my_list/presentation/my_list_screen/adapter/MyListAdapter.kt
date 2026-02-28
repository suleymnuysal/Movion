package com.suleymanuysal.movion.feature_my_list.presentation.my_list_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.databinding.MainCardViewRowBinding
import com.suleymanuysal.movion.databinding.MyListRowBinding
import com.suleymanuysal.movion.feature_movie.data.local.MovieEntity
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import com.suleymanuysal.movion.feature_movie.presentation.movie_screen.adapter.MoviesAdapter
import com.suleymanuysal.movion.feature_movie.presentation.movie_screen.adapter.MoviesAdapter.ItemViewHolder
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.domain.model.MyList

class MyListAdapter(
    private val myList: List<MyListEntity>,
    private val onclick : (String, Int, String, MyListEntity?) -> Unit,

    ) : RecyclerView.Adapter<MyListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = MyListRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val myList = myList[position]
        var imageUrl = ""
        var name = ""
        var releaseDate = ""
        var runtime = ""
        var voteAverage = ""

        if(myList.mediaType == "movie"){

            myList.posterPath?.let { it
                imageUrl = it
            }
            myList.title?.let { it
                name = it
            }
            myList.releaseDate?.let { it
                releaseDate = it
            }
            myList.runtime?.let { it
                runtime = it.toString()
            }
            myList.voteAverage?.let { it
                voteAverage = it.toString()
            }

        }else{
            myList.posterPath?.let { it
                imageUrl = it
            }
            myList.name?.let { it
                name = it
            }
            myList.firstAirDate?.let { it
                releaseDate = it
            }
            myList.voteAverage?.let { it
                voteAverage = it.toString()
            }
            runtime = "0"
        }

        Picasso.get()
            .load(Constants.IMAGE_BASE_URL+imageUrl)
            .into(holder.cardImageView)

        holder.name.text = name
        holder.releaseDate.text = releaseDate.substring(0,4)
        val hour = runtime.toInt() / 60
        val min = runtime.toInt() % 60
        holder.runtime.text = "${hour}h ${min}m"

        val rating = (5.0f*voteAverage.toFloat())/9.0f
        holder.ratingBar.rating = rating

        holder.cardView.setOnClickListener {
            onclick(myList.mediaType!!,myList.id,"cardView",null)
        }
        holder.remove.setOnClickListener {
            onclick(myList.mediaType!!,myList.id,"remove",myList)
        }

    }

    override fun getItemCount(): Int {
        return myList.size
    }

    inner class ItemViewHolder(binding: MyListRowBinding)
        : RecyclerView.ViewHolder(binding.root){

        val cardView  = binding.myListCardView
        val cardImageView  = binding.imageViewMyList
        val name  = binding.textViewMyListName
        val releaseDate  = binding.textViewMyListRelaseDate
        val runtime  = binding.textViewMyListRuntime
        val ratingBar = binding.ratingBar
        val remove = binding.myListRemove


    }
}
