package com.ibrahim.travelapp.domain.model.guidecategories

import com.google.gson.annotations.SerializedName

data class GuideCategories(

	@field:SerializedName("GuideCategories")
	val guideCategories: List<GuideCategoriesItem?>? = null
)