package com.ibrahim.travelapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.ibrahim.travelapp.R
import com.ibrahim.travelapp.databinding.FragmentHomeBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    lateinit var homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    private var allTravelList: List<AllTravelItem> = arrayListOf()
    private var flightList: List<AllTravelItem> = arrayListOf()
    private var hotelList: List<AllTravelItem> = arrayListOf()
    private var transportationList: List<AllTravelItem> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()

        initAdapter()
        fetchTravel()
        observeTravelList()

    }

    private fun initClickListeners() {
        fragmentHomeBinding.apply {
            homeImageviewFlight.setOnClickListener {Toast.makeText(requireContext(),"Flights",Toast.LENGTH_SHORT).show()}
            homeImageviewHotel.setOnClickListener {Toast.makeText(requireContext(),"Hotels",Toast.LENGTH_SHORT).show()}
            homeImageviewCar.setOnClickListener {Toast.makeText(requireContext(),"Cars",Toast.LENGTH_SHORT).show()}
            homeImageviewTaxi.setOnClickListener {Toast.makeText(requireContext(),"Taxi",Toast.LENGTH_SHORT).show()}
            homeTabLayout.addTab(homeTabLayout.newTab().setText("All"))
            homeTabLayout.addTab(homeTabLayout.newTab().setText("Flight"))
            homeTabLayout.addTab(homeTabLayout.newTab().setText("Hotels"))
            homeTabLayout.addTab(homeTabLayout.newTab().setText("Transportations"))

            homeTabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0 -> { homeAdapter.setList(homeViewModel.getAllTravel().value) }
                        1 -> { homeAdapter.setList(flightList) }
                        2 -> { homeAdapter.setList(hotelList) }
                        3 -> { homeAdapter.setList(transportationList) }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

        }
    }

    private fun observeTravelList() {
        homeViewModel.getAllTravel().observe(viewLifecycleOwner) { travelList ->
            homeAdapter.setList(travelList)
            flightList= travelList.filter { it.category == "flight" }
            hotelList= travelList.filter { it.category == "hotel" }
            transportationList= travelList.filter { it.category == "transportation" }
            fragmentHomeBinding.homeRecyclerview.isVisible = true
            fragmentHomeBinding.homeProgress.isVisible = false
        }
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter ()

        fragmentHomeBinding.homeRecyclerview.adapter = homeAdapter
        //Adapterin Ekrandaki Görünümü
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        fragmentHomeBinding.homeRecyclerview.layoutManager = layoutManager
    }

    private fun fetchTravel() {
        fragmentHomeBinding.homeRecyclerview.isVisible = false
        fragmentHomeBinding.homeProgress.isVisible = true
        homeViewModel.getAllTravel()

    }
}