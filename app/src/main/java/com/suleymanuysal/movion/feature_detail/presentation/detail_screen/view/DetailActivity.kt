package com.suleymanuysal.movion.feature_detail.presentation.detail_screen.view

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.suleymanuysal.movion.R
import com.suleymanuysal.movion.core.MainActivity
import com.suleymanuysal.movion.core.core_common.Constants
import com.suleymanuysal.movion.databinding.ActivityDetailBinding
import com.suleymanuysal.movion.feature_detail.domain.model.MovieDetail
import com.suleymanuysal.movion.feature_detail.domain.model.SeriesDetail
import com.suleymanuysal.movion.feature_detail.domain.model.toMyListEntity
import com.suleymanuysal.movion.feature_detail.presentation.detail_screen.adapter.DetailStateAdapter
import com.suleymanuysal.movion.feature_detail.presentation.view_model.DetailViewModel
import com.suleymanuysal.movion.feature_detail.presentation.episodes_screen.view.EpisodesFragment
import com.suleymanuysal.movion.feature_detail.presentation.more_detail_screen.view.MoreDetailFragment
import com.suleymanuysal.movion.feature_detail.presentation.related_screen.view.RelatedFragment
import com.suleymanuysal.movion.feature_detail.presentation.video_screen.TrailerActivity
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.presentation.my_list_screen.view_model.MyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.lang.Exception

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel : DetailViewModel by viewModels()
    private val myListViewModel : MyListViewModel by viewModels()
    private lateinit var detailStateAdapter: DetailStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbarDetail.setTitle("Detail")
        binding.toolbarDetail.setTitleTextColor(getColor(R.color.powder_blue))
        setSupportActionBar(binding.toolbarDetail)

        val type = intent.getStringExtra("type")
        val incomeId = intent.getIntExtra("id",0)

        checkExisting(incomeId)

        binding.viewPagerDetail.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        detailStateAdapter = DetailStateAdapter(supportFragmentManager,lifecycle,type!!,incomeId)
        binding.viewPagerDetail.adapter = detailStateAdapter

        TabLayoutMediator(binding.detailTabLayout,binding.viewPagerDetail
            ,true,true
        ) { tab, position ->

            if (type == "movie") {
                tab.text = when (position) {
                    0 -> "RELATED"
                    1 -> "MORE DETAIL"
                    else -> ""
                }
            } else {
                tab.text = when (position) {
                    0 -> "EPISODES"
                    1 -> "RELATED"
                    2 -> "MORE DETAIL"
                    else -> ""
                }
            }
        }.attach()

        binding.toolbarDetail.setNavigationOnClickListener {
            startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            finish()
        }

        observeDetail(type)

        if(type == "movie") {
            detailViewModel.getMovieDetail(incomeId)
        }else if(type == "tv"){
            detailViewModel.getSeriesDetail(incomeId)
        }

        detailViewModel.getVideos(type,incomeId)

        binding.watchTrailer.setOnClickListener {
            val intentToTrailer = Intent(this@DetailActivity, TrailerActivity::class.java)
            intentToTrailer.putExtra("type",type)
            intentToTrailer.putExtra("id",incomeId)
            startActivity(intentToTrailer)
        }

    }

    private fun addToMyList(item : MyListEntity){
        lifecycleScope.launch(Dispatchers.IO) {
            myListViewModel.addToMyList(item)
            withContext(Dispatchers.Main){
                binding.detailAddToMyList.visibility = View.GONE
                binding.detailRemoveFromMyList.visibility = View.VISIBLE
                Snackbar.make(binding.detailAddToMyList,
                    "Successfully added to My List",
                    Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.snack_bar)).show()

            }
        }
    }

    private fun removeFromMyList(item : MyListEntity){
        lifecycleScope.launch(Dispatchers.IO) {
            myListViewModel.removeFromMyList(item)
            withContext(Dispatchers.Main){
                binding.detailAddToMyList.visibility = View.VISIBLE
                binding.detailRemoveFromMyList.visibility = View.GONE
                Snackbar.make(binding.detailRemoveFromMyList,
                    "Successfully removed from My List",
                    Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.snack_bar)).show()
            }
        }
    }

    private fun checkExisting(id: Int){
        lifecycleScope.launch {
            myListViewModel.checkExisting(id)
            myListViewModel.isStored.collect { it
                if(it){
                    binding.detailAddToMyList.visibility = View.GONE
                    binding.detailRemoveFromMyList.visibility = View.VISIBLE
                }else{
                    binding.detailAddToMyList.visibility = View.VISIBLE
                    binding.detailRemoveFromMyList.visibility = View.GONE
                }

            }

        }

    }

    private fun observeDetail(type: String){
        lifecycleScope.launch {
            if(type == "movie"){
                detailViewModel.movieDetail.collect { movieDetail ->
                    bindItems("movie",movieDetail,null)

                    binding.detailAddToMyList.setOnClickListener {
                        addToMyList(movieDetail.toMyListEntity())
                    }

                    binding.detailRemoveFromMyList.setOnClickListener {
                        removeFromMyList(movieDetail.toMyListEntity())
                    }
                }
                
            }else if(type == "tv"){
                detailViewModel.serisDetail.collect { seriesDetail ->
                    bindItems("tv", null,seriesDetail)

                    binding.detailAddToMyList.setOnClickListener {
                        addToMyList(seriesDetail.toMyListEntity())
                    }

                    binding.detailRemoveFromMyList.setOnClickListener {
                        removeFromMyList(seriesDetail.toMyListEntity())
                    }
                }
            }
        }
    }

    private fun bindItems(type: String, movieDetail: MovieDetail?, seriesDetail: SeriesDetail?){

        var imageUrl = ""
        var name = ""
        var date = ""
        var runtime = ""
        var language = ""
        var overview = ""
        var adult = ""
        var voteAverage = ""
        val genres = arrayListOf("")

        if(type == "movie" && movieDetail != null){

            movieDetail.posterPath?.let { it
                imageUrl = it
            }

            movieDetail.runtime?.let { it
                val hour: Int = it / 60
                val minutes: Int = (it - (hour * 60))
                runtime = "${hour}h ${minutes}m"
            }

            movieDetail.originalLanguage?.let {
                language = it
            }

            if (movieDetail.releaseDate?.isNotEmpty() == true){
                date = movieDetail.releaseDate.take(4)
            }

            name = movieDetail.title ?: "Unknown"
            overview = movieDetail.overview ?: ""
            adult = if(movieDetail.adult == true) "+18" else "+13"

            movieDetail.voteAverage?.let {it
                val format = DecimalFormat("#.#")
                voteAverage = format.format(it)
            }

            movieDetail.genres?.forEach { (id, name) ->
                if (name.isNotEmpty()) genres.add(name)
            }

        }else if(type == "tv" && seriesDetail != null){

            seriesDetail.posterPath?.let { image->
                imageUrl = image
            }

            if (seriesDetail.firstAirdate?.isNotEmpty() == true){
                date = seriesDetail.firstAirdate.take(4)
            }

            name = seriesDetail.name ?: "Unknown"
            overview = seriesDetail.overview ?: ""
            runtime = "${seriesDetail.numberOfSeasons} Seasons"
            adult = if(seriesDetail.adult == true) "+18" else "+13"

            seriesDetail.originalLanguage?.let {
                language = it
            }

            seriesDetail.voteAverage?.let {
                val format = DecimalFormat("#.#")
                voteAverage = format.format(it)
            }

            seriesDetail.genres?.forEach { (id, name) ->
                if (name.isNotEmpty()) genres.add(name)
            }
        }

        Picasso.get()
            .load(Constants.IMAGE_ORIGINAL_URL+imageUrl)
            .into(binding.imageViewDetail,object : Callback{
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE
                }
                override fun onError(e: Exception?) {}

            })

        binding.textViewDetailName.text = name
        binding.textViewDetailDate.text = date
        binding.textViewDetailRuntime.text = runtime
        binding.textViewLanguage.text = language.uppercase()
        binding.textViewDetailOverview.text = overview
        binding.textViewDetailAdult.text = adult
        binding.textViewDetailVoteAvrange.text = voteAverage

        if(genres.toString().length >= 4) {
            binding.textviewDetailGenres.text = genres.toString().substring(3, (genres.toString().length - 1))
        }else{
            binding.textviewDetailGenres.text = ""
        }

    }


}