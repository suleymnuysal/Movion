package com.suleymanuysal.movion.feature_detail.presentation.related_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.suleymanuysal.movion.core.core_common.toDetail
import com.suleymanuysal.movion.databinding.FragmentRelatedBinding
import com.suleymanuysal.movion.feature_detail.presentation.view_model.DetailViewModel
import com.suleymanuysal.movion.feature_detail.presentation.related_screen.adapter.RelatedAdapter
import com.suleymanuysal.movion.feature_movie.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RelatedFragment : Fragment() {

    private var _binding: FragmentRelatedBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel : DetailViewModel by viewModels()
    private lateinit var relatedAdapter: RelatedAdapter
    private var type = ""
    private var id = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRelatedBinding.inflate(inflater,container,false)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            3,
            StaggeredGridLayoutManager.VERTICAL
        )

        arguments?.let { it
            type = it.getString("type") ?: "movie"
            id = it.getInt("id")

        }

        observeRelated(type)

        detailViewModel.getRelatedMovies(id)
        detailViewModel.getRelatedSeries(id)


        return binding.root
    }


    private fun observeRelated(type: String){
        lifecycleScope.launch {
            if(type == "movie"){
                detailViewModel.relatedMovies.collect { movies ->
                    relatedAdapter = RelatedAdapter(movies) { id,isLoaded ->
                        isLoaded?.let {
                           if (isLoaded)  binding.progressBar.visibility = View.INVISIBLE
                        }
                        id?.let {
                            toDetail(requireContext(),"movie",id)
                        }
                    }
                    binding.recyclerView.adapter = relatedAdapter
                }
            }else if(type == "tv"){
                detailViewModel.relatedSeries.collect { series ->
                    relatedAdapter = RelatedAdapter(series.map { it -> Movie(it.id,it.poster_path) })
                    { id, isLoaded ->
                        isLoaded?.let {
                            if (isLoaded)  binding.progressBar.visibility = View.GONE
                        }
                        id?.let {
                            toDetail(requireContext(),"tv",id)
                        }

                    }
                    binding.recyclerView.adapter = relatedAdapter

                }
            }
        }
    }

    companion object{
        fun newInstance(type:String,id:Int) = RelatedFragment().apply{
            arguments = Bundle().apply {
                putString("type",type)
                putInt("id",id)
            }
        }
    }



}