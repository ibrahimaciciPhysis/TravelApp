package com.ibrahim.travelapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.travelapp.domain.model.guidecategories.GuideCategoriesItem
import com.ibrahim.travelapp.domain.usecase.AllTravelUseCase
import com.ibrahim.travelapp.domain.usecase.GuideCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel@Inject constructor(
    private val allTravelUseCase: AllTravelUseCase,
    private val guideCategoriesUseCase: GuideCategoriesUseCase
): ViewModel() {

    fun getAllTravel(): LiveData<List<AllTravelItem>> {
        allTravelUseCase.apply {
            getAllTravel()
            return allTravel
        }
    }

    fun getGuideCategories(): LiveData<List<GuideCategoriesItem>> {
        guideCategoriesUseCase.apply {
            getGuideCategories()
            return guideCategories
        }
    }
}