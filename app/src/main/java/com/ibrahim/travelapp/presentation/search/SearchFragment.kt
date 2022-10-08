package com.ibrahim.travelapp.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.travelapp.databinding.FragmentSearchBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :Fragment() {

    private lateinit var fragmentSearchBinding: FragmentSearchBinding

    lateinit var searchDestinationAdapter: SearchDestinationAdapter
    lateinit var searchAttractionAdapter: SearchAttractionAdapter
    private val searchViewModel: SearchViewModel by viewModels()
    private var topDestinationList: List<AllTravelItem> = arrayListOf()
    private var nearbyList: List<AllTravelItem> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return fragmentSearchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()

        initAdapter()
        fetchTravel()
        observeTravelList()

    }

    private fun initClickListeners() {

    }


    private fun observeTravelList() {
        searchViewModel.getAllTravel().observe(viewLifecycleOwner) { travelList ->
            topDestinationList= travelList.filter { it.category == "topdestination" }
            nearbyList= travelList.filter { it.category == "nearby" }
            searchDestinationAdapter.setList(topDestinationList)
            searchAttractionAdapter.setList(nearbyList)
            fragmentSearchBinding.searchRecyclerviewDestinations.isVisible = true
            fragmentSearchBinding.searchRecyclerviewAttractions.isVisible = true
            fragmentSearchBinding.searchProgressDestinations.isVisible = false
            fragmentSearchBinding.searchProgressAttractions.isVisible = false
        }
    }

    private fun initAdapter() {
        searchDestinationAdapter = SearchDestinationAdapter()
        searchAttractionAdapter= SearchAttractionAdapter()
        fragmentSearchBinding.searchRecyclerviewDestinations.adapter = searchDestinationAdapter
        fragmentSearchBinding.searchRecyclerviewAttractions.adapter = searchAttractionAdapter
        //Adapterin Ekrandaki Görünümü
        val layoutManagerDestination = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val layoutManagerAttraction = LinearLayoutManager(context)
        fragmentSearchBinding.searchRecyclerviewDestinations.layoutManager = layoutManagerDestination
        fragmentSearchBinding.searchRecyclerviewAttractions.layoutManager = layoutManagerAttraction
    }

    private fun fetchTravel() {
        fragmentSearchBinding.searchRecyclerviewDestinations.isVisible = false
        fragmentSearchBinding.searchRecyclerviewAttractions.isVisible = false
        fragmentSearchBinding.searchProgressDestinations.isVisible = true
        fragmentSearchBinding.searchProgressAttractions.isVisible = true
        searchViewModel.getAllTravel()

    }
}
