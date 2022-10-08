package com.ibrahim.travelapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahim.travelapp.data.db.entity.TravelEntity

@Dao
interface TravelDao {
    @Query("SELECT * FROM travel_table where isBookmark = :isTrue")
    fun getTravelsByIsBookmark(isTrue:Boolean): LiveData<List<TravelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTravel(travel:TravelEntity)

    @Query("DELETE from travel_table where travelId = :travelId")
    fun deleteById(vararg travelId :Int) :Int
}