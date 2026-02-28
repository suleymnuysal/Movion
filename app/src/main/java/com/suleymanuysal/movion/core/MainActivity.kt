package com.suleymanuysal.movion.core

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.suleymanuysal.movion.R
import com.suleymanuysal.movion.databinding.ActivityMainBinding
import com.suleymanuysal.movion.feature_movie.presentation.movie_screen.view.MoviesFragment
import com.suleymanuysal.movion.feature_my_list.presentation.my_list_screen.view.MyListFragment
import com.suleymanuysal.movion.feature_series.presentation.series_screen.view.SeriesFragment
import com.suleymanuysal.movion.feature_trending.presentation.trending_screen.view.TrendingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var activeFragment : Fragment?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        switchFragment("Movies"){
            MoviesFragment()
        }
        setUpBottomNav()

    }
    private fun setUpBottomNav(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.movies -> switchFragment("Movies"){ MoviesFragment() }
                R.id.series -> switchFragment("Series"){ SeriesFragment() }
                R.id.trending -> switchFragment("Trending"){ TrendingFragment() }
                R.id.myList -> switchFragment("My List"){ MyListFragment() }
            }
            true
        }
    }

    private fun switchFragment(tag: String, fragmentProvider: () -> Fragment){

       val fm = supportFragmentManager
       val transaction = supportFragmentManager.beginTransaction()
       var fragment = fm.findFragmentByTag(tag)
        if (fragment == null){
            fragment = fragmentProvider()
            transaction.add(R.id.mainFragmentContainerView,fragment,tag)
        }
        activeFragment?.let { it
            transaction.hide(it)
        }
        transaction.show(fragment).commit()
        activeFragment = fragment
        binding.include.textViewTitle.text = activeFragment?.tag
    }


}