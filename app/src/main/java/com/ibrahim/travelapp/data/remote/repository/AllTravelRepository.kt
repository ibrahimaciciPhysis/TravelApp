package com.ibrahim.travelapp.data.remote.repository

import com.ibrahim.travelapp.data.remote.TravelApiService
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import retrofit2.Call
import javax.inject.Inject

class AllTravelRepository @Inject constructor(private val travelApiService: TravelApiService) {

    fun getAllTravel(): Call<List<AllTravelItem>> {
        return travelApiService.getAllTravel()
    }

}
