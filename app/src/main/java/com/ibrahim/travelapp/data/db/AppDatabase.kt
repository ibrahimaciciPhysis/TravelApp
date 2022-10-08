package com.ibrahim.travelapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahim.travelapp.data.db.dao.TravelDao
import com.ibrahim.travelapp.data.db.entity.TravelEntity

@Database(entities = [TravelEntity::class],version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun traveldao(): TravelDao

}