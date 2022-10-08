package com.ibrahim.travelapp.domain.model.guidecategories

import com.google.gson.annotations.SerializedName

data class GuideCategoriesItem(

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)