package com.ibrahim.travelapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibrahim.travelapp.domain.model.alltravel.ImagesItem

@Entity(tableName = "travel_table")
data class TravelEntity(
    @PrimaryKey(autoGenerate = false)
    var travelId:String="0",

    @ColumnInfo(name ="title")
    var title: String,

    @ColumnInfo(name ="country")
    var country: String,

    @ColumnInfo(name ="city")
    var city: String,

    @ColumnInfo(name ="imageurl")
    var imageurl: String,

    @ColumnInfo(name ="description")
    var description: String,

    @ColumnInfo(name ="isBookmark")
    var isBookmark: Boolean?,
    )