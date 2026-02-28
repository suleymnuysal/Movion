package com.suleymanuysal.movion.feature_detail.presentation.more_detail_screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.suleymanuysal.movion.databinding.FragmentMoreDetailBinding
import com.suleymanuysal.movion.feature_detail.presentation.more_detail_screen.adapter.MoreDetailAdapter
import com.suleymanuysal.movion.feature_detail.presentation.view_model.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreDetailFragment : Fragment() {
    private var _binding: FragmentMoreDetailBinding? = null
    private val binding get() = _binding!!
    private var type = ""
    private var id = 0
    private val detailViewModel : DetailViewModel by viewModels()
    private lateinit var creditAdapter: MoreDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreDetailBinding.inflate(inflater,container,false)

        binding.recyclerViewCastAndCrew.setHasFixedSize(true)
        binding.recyclerViewCastAndCrew.layoutManager = StaggeredGridLayoutManager(3,
            StaggeredGridLayoutManager.VERTICAL)

        arguments?.let { it
            type = it.getString("type","movie")
            id = it.getInt("id")
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailViewModel.credits.collect { credits ->
                   creditAdapter = MoreDetailAdapter(credits){id,isLoad ->

                       isLoad?.let {
                           if (isLoad) binding.progressBarCastAndCrew.visibility = View.GONE
                       }
                   }
                   binding.recyclerViewCastAndCrew.adapter = creditAdapter
                }
            }
        }
        detailViewModel.getCredits(type,id)


        return binding.root
    }

    companion object {
        fun newInstance(type: String, id: Int) = MoreDetailFragment().apply {
            arguments = Bundle().apply {
                putString("type",type)
                putInt("id",id)
            }
        }

    }


}