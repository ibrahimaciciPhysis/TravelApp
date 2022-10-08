package com.ibrahim.travelapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibrahim.travelapp.data.remote.repository.AllTravelRepository
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AllTravelUseCase @Inject constructor(
    private val allTravelRepository: AllTravelRepository
) {

    // val topRatedMoviesModelItemList = MutableLiveData<List<TopRatedMoviesModelItem?>>()
    private var _allTravel = MutableLiveData<List<AllTravelItem>>()
    val allTravel: LiveData<List<AllTravelItem>> = _allTravel

    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getAllTravel(){
        allTravelRepository.getAllTravel().enqueue(object:Callback<List<AllTravelItem>> {
            override fun onResponse(
                call: Call<List<AllTravelItem>>,
                response: Response<List<AllTravelItem>>
            ) {
                _allTravel.value = response.body()
                // topRatedMoviesModelItemList.value = response.body()?.results ?: emptyList()
            }

            override fun onFailure(call: Call<List<AllTravelItem>>, t: Throwable) {
                //Log.v("ERROR",t.message.toString())
                errorLiveData.value = "Filmler çekilemedi."
            }

        })
    }


}