package com.suleymanuysal.movion.feature_detail.presentation.detail_screen.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.suleymanuysal.movion.feature_detail.presentation.episodes_screen.view.EpisodesFragment
import com.suleymanuysal.movion.feature_detail.presentation.more_detail_screen.view.MoreDetailFragment
import com.suleymanuysal.movion.feature_detail.presentation.related_screen.view.RelatedFragment

class DetailStateAdapter(fm: FragmentManager,
                         lc: Lifecycle,
                         val type: String,
                         val id:Int
) : FragmentStateAdapter(fm,lc) {

    override fun createFragment(position: Int): Fragment {
        return if(type == "movie"){
            when(position){
                0 -> RelatedFragment.newInstance(type,id)
                1 -> MoreDetailFragment.newInstance(type,id)
                else -> Fragment()
            }
        }else{
            when(position){
                0 -> EpisodesFragment.newInstance(id)
                1 -> RelatedFragment.newInstance(type,id)
                2 -> MoreDetailFragment.newInstance(type,id)
                else -> Fragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return if(type == "movie") 2 else 3

    }
}