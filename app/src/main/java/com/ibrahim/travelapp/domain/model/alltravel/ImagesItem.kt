package com.ibrahim.travelapp.domain.model.alltravel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ImagesItem(

	@field:SerializedName("altText")
	val altText: @RawValue Any? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("isHeroImage")
	val isHeroImage: Boolean? = null
):Parcelable