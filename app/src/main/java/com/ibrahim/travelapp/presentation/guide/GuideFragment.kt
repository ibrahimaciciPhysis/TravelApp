package com.ibrahim.travelapp.presentation.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.travelapp.databinding.FragmentGuideBinding
import com.ibrahim.travelapp.databinding.FragmentSearchBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.travelapp.presentation.search.SearchAttractionAdapter
import com.ibrahim.travelapp.presentation.search.SearchDestinationAdapter
import com.ibrahim.travelapp.presentation.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment :Fragment() {

    private lateinit var fragmentGuideBinding: FragmentGuideBinding

    lateinit var guideMightAdapter: GuideMightAdapter
    lateinit var guideArticleAdapter: GuideArticleAdapter
    lateinit var guideCategoriesAdapter: GuideCategoriesAdapter
    private val guideViewModel: GuideViewModel by viewModels()
    private var mightNeedList: List<AllTravelItem> = arrayListOf()
    private var topPickList: List<AllTravelItem> = arrayListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentGuideBinding = FragmentGuideBinding.inflate(inflater, container, false)
        return fragmentGuideBinding.root
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
        guideViewModel.getAllTravel().observe(viewLifecycleOwner) { travelList ->
            mightNeedList= travelList.filter { it.category == "mightneed" }
            topPickList= travelList.filter { it.category == "toppick" }
            guideMightAdapter.setList(mightNeedList)
            guideArticleAdapter.setList(topPickList)
            fragmentGuideBinding.guideRecyclerviewMight.isVisible = true
            fragmentGuideBinding.guideRecyclerviewArticles.isVisible = true
            fragmentGuideBinding.guideProgressMight.isVisible = false
            fragmentGuideBinding.guideProgressArticles.isVisible = false
        }
        guideViewModel.getGuideCategories().observe(viewLifecycleOwner){guideList ->
            guideCategoriesAdapter.setList(guideList)
            fragmentGuideBinding.guideRecyclerviewCategories.isVisible = true
            fragmentGuideBinding.guideProgressCategories.isVisible = false
        }
    }

    private fun initAdapter() {
        guideMightAdapter = GuideMightAdapter()
        guideArticleAdapter = GuideArticleAdapter()
        guideCategoriesAdapter = GuideCategoriesAdapter()

        fragmentGuideBinding.guideRecyclerviewMight.adapter = guideMightAdapter
        fragmentGuideBinding.guideRecyclerviewArticles.adapter = guideArticleAdapter
        fragmentGuideBinding.guideRecyclerviewCategories.adapter = guideCategoriesAdapter

        //Adapterin Ekrandaki Görünümü
        val layoutManagerDestination = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val layoutManagerAttraction = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val layoutManagerCategories = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        fragmentGuideBinding.guideRecyclerviewMight.layoutManager = layoutManagerDestination
        fragmentGuideBinding.guideRecyclerviewArticles.layoutManager = layoutManagerAttraction
        fragmentGuideBinding.guideRecyclerviewCategories.layoutManager = layoutManagerCategories
    }

    private fun fetchTravel() {
        fragmentGuideBinding.guideRecyclerviewMight.isVisible = false
        fragmentGuideBinding.guideRecyclerviewArticles.isVisible = false
        fragmentGuideBinding.guideRecyclerviewCategories.isVisible = false
        fragmentGuideBinding.guideProgressMight.isVisible = true
        fragmentGuideBinding.guideProgressArticles.isVisible = true
        fragmentGuideBinding.guideProgressCategories.isVisible = true
        guideViewModel.getAllTravel()
        guideViewModel.getGuideCategories()

    }
}