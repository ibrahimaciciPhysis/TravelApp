package com.ibrahim.travelapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.travelapp.domain.usecase.AllTravelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel@Inject constructor(
    private val allTravelUseCase: AllTravelUseCase
): ViewModel() {

    fun getAllTravel(): LiveData<List<AllTravelItem>> {
        allTravelUseCase.apply {
            getAllTravel()
            return allTravel
        }
    }
}