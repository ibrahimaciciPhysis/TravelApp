package com.ibrahim.travelapp.presentation.trip

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ibrahim.travelapp.data.db.Database
import dagger.hilt.android.internal.Contexts.getApplication

class TripViewModel(application: Application) :AndroidViewModel(application) {
    fun getTravelsByIsBookmark(isTrue:Boolean) = Database.getDatabase(getApplication()).traveldao().getTravelsByIsBookmark(isTrue)
}