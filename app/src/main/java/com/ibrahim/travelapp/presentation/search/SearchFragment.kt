package com.ibrahim.travelapp.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.travelapp.databinding.FragmentSearchBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravel
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :Fragment() {

    private lateinit var fragmentSearchBinding: FragmentSearchBinding

    lateinit var searchDestinationAdapter: SearchDestinationAdapter
    lateinit var searchAttractionAdapter: SearchAttractionAdapter
    lateinit var searchAdapter: SearchAdapter
    private val searchViewModel: SearchViewModel by viewModels()
    private var topDestinationList: List<AllTravelItem> = arrayListOf()
    private var nearbyList: List<AllTravelItem> = arrayListOf()
    private var travelList: List<AllTravelItem> = arrayListOf()

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
        fragmentSearchBinding.searchEdittextWhere.doAfterTextChanged { it->
            it?.let { search->
                fragmentSearchBinding.apply {
                if (search.isNotEmpty()){
                    searchRecyclerviewSearch.visibility = View.VISIBLE
                    searchTextviewTop.visibility = View.INVISIBLE
                    searchRecyclerviewDestinations.visibility = View.INVISIBLE
                    searchTextviewNearby.visibility = View.INVISIBLE
                    searchRecyclerviewAttractions.visibility = View.INVISIBLE
                    searchAdapter.setList(travelList.filter { it.title?.lowercase()?.startsWith(search.toString().lowercase()) == true })
                }else{
                    searchRecyclerviewSearch.visibility = View.INVISIBLE
                    searchTextviewTop.visibility = View.VISIBLE
                    searchRecyclerviewDestinations.visibility = View.VISIBLE
                    searchTextviewNearby.visibility = View.VISIBLE
                    searchRecyclerviewAttractions.visibility = View.VISIBLE
                }
                }
            }
        }

    }


    private fun observeTravelList() {
        searchViewModel.getAllTravel().observe(viewLifecycleOwner) { allTravelList ->
            topDestinationList= allTravelList.filter { it.category == "topdestination" }
            nearbyList = allTravelList.filter { it.category == "nearby" }
            travelList = allTravelList
            searchDestinationAdapter.setList(topDestinationList)
            searchAttractionAdapter.setList(nearbyList)
            //searchAdapter.setList(travelList)
            fragmentSearchBinding.searchTextviewTop.isVisible = true
            fragmentSearchBinding.searchTextviewNearby.isVisible = true
            fragmentSearchBinding.searchRecyclerviewSearch.isVisible = false
            fragmentSearchBinding.searchRecyclerviewDestinations.isVisible = true
            fragmentSearchBinding.searchRecyclerviewAttractions.isVisible = true
            fragmentSearchBinding.searchProgressDestinations.isVisible = false
            fragmentSearchBinding.searchProgressAttractions.isVisible = false

        }
    }

    private fun initAdapter() {
        searchDestinationAdapter = SearchDestinationAdapter()
        searchAttractionAdapter= SearchAttractionAdapter()
        searchAdapter= SearchAdapter()
        fragmentSearchBinding.searchRecyclerviewDestinations.adapter = searchDestinationAdapter
        fragmentSearchBinding.searchRecyclerviewAttractions.adapter = searchAttractionAdapter
        fragmentSearchBinding.searchRecyclerviewSearch.adapter = searchAdapter
        //Adapterin Ekrandaki Görünümü
        val layoutManagerDestination = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val layoutManagerAttraction = LinearLayoutManager(context)
        val layoutManager = LinearLayoutManager(context)
        fragmentSearchBinding.searchRecyclerviewDestinations.layoutManager = layoutManagerDestination
        fragmentSearchBinding.searchRecyclerviewAttractions.layoutManager = layoutManagerAttraction
        fragmentSearchBinding.searchRecyclerviewSearch.layoutManager = layoutManager
    }

    private fun fetchTravel() {
        fragmentSearchBinding.searchRecyclerviewDestinations.isVisible = false
        fragmentSearchBinding.searchRecyclerviewAttractions.isVisible = false
        fragmentSearchBinding.searchProgressDestinations.isVisible = true
        fragmentSearchBinding.searchProgressAttractions.isVisible = true
        searchViewModel.getAllTravel()

    }
}
