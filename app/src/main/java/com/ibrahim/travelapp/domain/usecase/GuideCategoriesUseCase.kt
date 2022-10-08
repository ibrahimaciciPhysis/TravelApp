package com.ibrahim.travelapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibrahim.travelapp.data.remote.repository.AllTravelRepository
import com.ibrahim.travelapp.data.remote.repository.GuideCategoriesRepository
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.travelapp.domain.model.guidecategories.GuideCategoriesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GuideCategoriesUseCase@Inject constructor(
    private val guideCategoriesRepository: GuideCategoriesRepository
) {

    private var _guideCategories = MutableLiveData<List<GuideCategoriesItem>>()
    val guideCategories: LiveData<List<GuideCategoriesItem>> = _guideCategories

    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getGuideCategories(){
        guideCategoriesRepository.getGuideCategories().enqueue(object:Callback<List<GuideCategoriesItem>> {
            override fun onResponse(
                call: Call<List<GuideCategoriesItem>>,
                response: Response<List<GuideCategoriesItem>>
            ) {
                _guideCategories.value = response.body()
            }

            override fun onFailure(call: Call<List<GuideCategoriesItem>>, t: Throwable) {
                //Log.v("ERROR",t.message.toString())
                errorLiveData.value = "BAĞLANTI HATASI"
            }

        })
    }


}