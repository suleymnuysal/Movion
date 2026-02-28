package com.suleymanuysal.movion.feature_detail.presentation.episodes_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.suleymanuysal.movion.databinding.FragmentEpisodesBinding
import com.suleymanuysal.movion.feature_detail.presentation.episodes_screen.adapter.EpisodesAdapter
import com.suleymanuysal.movion.feature_detail.presentation.view_model.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : Fragment() {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel : DetailViewModel by viewModels()
    private lateinit var episodesAdapter: EpisodesAdapter
    private var id = 0
    private val seasonList : ArrayList<String> = ArrayList()
    private lateinit var arrayAdapter : ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodesBinding.inflate(inflater,container,false)

        binding.episodeRv.setHasFixedSize(true)
        binding.episodeRv.layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)

        arguments?.let { it
            id = it.getInt("id")
        }

        observeEpisodes()
        detailViewModel.getEpisodes(id,1)
        observeEpisodeDetail()
        detailViewModel.getSeriesDetail(id)




        return binding.root
    }


    private fun observeEpisodes(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailViewModel.episodes.collect { episodes ->
                    episodesAdapter = EpisodesAdapter(episodes){id,isLoad ->
                        isLoad?.let {
                            if (isLoad) binding.progressBarEpisodes.visibility = View.GONE
                        }
                    }
                    binding.episodeRv.adapter = episodesAdapter
                }
            }
        }
    }

    private fun observeEpisodeDetail(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailViewModel.serisDetail.collect { seriesDetail ->

                    for (i in 1..seriesDetail.numberOfSeasons) {
                        seasonList.add("Season $i")
                    }
                    arrayAdapter = ArrayAdapter(requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        seasonList)

                    binding.spinnerTvSeasons.adapter = arrayAdapter

                    binding.spinnerTvSeasons.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            seasonNumber: Int,
                            p3: Long
                        ) {
                            detailViewModel.getEpisodes(id,seasonNumber+1)
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }
                    }
                }
            }
        }
    }

    companion object{
        fun newInstance(id:Int) = EpisodesFragment().apply{
            arguments = Bundle().apply {
                putInt("id",id)
            }
        }
    }

}