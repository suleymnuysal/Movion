package com.suleymanuysal.movion.feature_my_list.presentation.my_list_screen.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.suleymanuysal.movion.R
import com.suleymanuysal.movion.core.core_common.toDetail
import com.suleymanuysal.movion.databinding.FragmentMyListBinding
import com.suleymanuysal.movion.feature_detail.presentation.detail_screen.view.DetailActivity
import com.suleymanuysal.movion.feature_my_list.data.local.MyListEntity
import com.suleymanuysal.movion.feature_my_list.presentation.my_list_screen.adapter.MyListAdapter
import com.suleymanuysal.movion.feature_my_list.presentation.my_list_screen.view_model.MyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class MyListFragment : Fragment() {
    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!
    private val myListViewModel : MyListViewModel by viewModels()
    private lateinit var myListAdapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyListBinding.inflate(inflater,container,false)

        binding.recyclerViewMyList.setHasFixedSize(true)
        binding.recyclerViewMyList.layoutManager = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                myListViewModel.myList.collect { myList ->

                    myListAdapter = MyListAdapter(myList) { type,id,where,myListEntity ->
                        clickHandler(type,id,where,myListEntity)
                    }
                    binding.recyclerViewMyList.adapter = myListAdapter

                    binding.textViewAll.setOnClickListener {
                        binding.textViewAll.setTextColor(getResources().getColor(R.color.white))
                        binding.textViewMovies.setTextColor(getResources().getColor(R.color.in_box_text_color))
                        binding.textViewTvs.setTextColor(getResources().getColor(R.color.in_box_text_color))

                        myListAdapter = MyListAdapter(myList) { type,id,where,myListEntity ->
                            clickHandler(type,id,where,myListEntity)
                        }
                        binding.recyclerViewMyList.adapter = myListAdapter

                    }
                    binding.textViewMovies.setOnClickListener {
                        binding.textViewMovies.setTextColor(getResources().getColor(R.color.white))
                        binding.textViewAll.setTextColor(getResources().getColor(R.color.in_box_text_color))
                        binding.textViewTvs.setTextColor(getResources().getColor(R.color.in_box_text_color))

                        val movies = myList.filter { it.mediaType == "movie" }
                        myListAdapter = MyListAdapter(movies) { type,id,where,myListEntity ->
                            clickHandler(type,id,where,myListEntity)
                        }
                        binding.recyclerViewMyList.adapter = myListAdapter
                    }
                    binding.textViewTvs.setOnClickListener {
                        binding.textViewTvs.setTextColor(getResources().getColor(R.color.white))
                        binding.textViewAll.setTextColor(getResources().getColor(R.color.in_box_text_color))
                        binding.textViewMovies.setTextColor(getResources().getColor(R.color.in_box_text_color))

                        val tvs = myList.filter { it.mediaType == "tv" }
                        myListAdapter = MyListAdapter(tvs) { type,id,where,myListEntity ->
                            clickHandler(type,id,where,myListEntity)
                        }
                        binding.recyclerViewMyList.adapter = myListAdapter
                    }

                }
            }
        }


        return binding.root
    }

    private fun clickHandler(type : String,
                             id : Int, where : String,
                             myListEntity: MyListEntity?)
    {
        if(where == "cardView"){
            toDetail(requireContext(),type,id)
        }else{
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Delete this item?")
            alertDialog.setMessage("Are you sure you want to delete this item?")
            alertDialog.setPositiveButton("Yes"
            ) { p0, p1 ->
                lifecycleScope.launch(Dispatchers.IO) {
                    myListEntity?.let { it
                        myListViewModel.removeFromMyList(it)
                    }
                }
            }
            alertDialog.setNegativeButton("No"){ po,p1 ->
                alertDialog.create().dismiss()
            }
            alertDialog.show()
        }

    }


}