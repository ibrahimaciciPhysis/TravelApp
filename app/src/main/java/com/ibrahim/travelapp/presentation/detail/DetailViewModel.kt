package com.ibrahim.travelapp.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem

class DetailViewModel (application: Application) : AndroidViewModel(application)  {
    val plannedTravelItem = MutableLiveData<AllTravelItem>()
    val plannedTravelItemImage = MutableLiveData<String>()
}