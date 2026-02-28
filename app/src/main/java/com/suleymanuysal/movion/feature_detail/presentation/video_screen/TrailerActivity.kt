package com.suleymanuysal.movion.feature_detail.presentation.video_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.suleymanuysal.movion.core.MainActivity
import com.suleymanuysal.movion.databinding.ActivityTrailerBinding
import com.suleymanuysal.movion.feature_detail.presentation.detail_screen.view.DetailActivity
import com.suleymanuysal.movion.feature_detail.presentation.view_model.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class TrailerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrailerBinding
    private var youTubePlayer: YouTubePlayer? = null
    private var isFullscreen = false
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarTrailer.title = "Official Trailer"
        binding.toolbarTrailer.setNavigationOnClickListener {
            startActivity(Intent(this@TrailerActivity, DetailActivity::class.java))
            finish()
        }

        val type = intent.getStringExtra("type")
        val incomeId = intent.getIntExtra("id",0)

        detailViewModel.getVideos(type!!,incomeId)

        val iFramePlayerOptions = IFramePlayerOptions.Builder(applicationContext)
            .controls(1)
            .fullscreen(1)
            .build()

        binding.youtubePlayerView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullscreen = true

                binding.youtubePlayerView.visibility = View.GONE
                binding.fullScreenViewContainer.visibility = View.VISIBLE
                binding.toolbarTrailer.visibility = View.GONE
                binding.fullScreenViewContainer.addView(fullscreenView)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            }

            override fun onExitFullscreen() {
                isFullscreen = false
                
                binding.youtubePlayerView.visibility = View.VISIBLE
                binding.toolbarTrailer.visibility = View.VISIBLE
                binding.fullScreenViewContainer.visibility = View.GONE
                binding.fullScreenViewContainer.removeAllViews()
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        })

        binding.youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@TrailerActivity.youTubePlayer = youTubePlayer

                lifecycleScope.launch(Dispatchers.IO){
                    detailViewModel.videos.collect { videos ->
                        videos.forEach { video ->
                            if(video.site == "YouTube"|| video.type == "Trailer"){
                                withContext(Dispatchers.Main){
                                    youTubePlayer.loadVideo(video.key, 0f)
                                }
                            }
                        }
                    }
                }
            }
        }, iFramePlayerOptions)

        lifecycle.addObserver(binding.youtubePlayerView)
    }

}