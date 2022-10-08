package com.ibrahim.travelapp.presentation.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.travelapp.databinding.GuideArticlesItemBinding
import com.ibrahim.travelapp.databinding.GuideCategoriesItemBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.travelapp.domain.model.guidecategories.GuideCategoriesItem

class GuideCategoriesAdapter() : RecyclerView.Adapter<GuideCategoriesAdapter.GuideVH>() {

    private var guideCategoriesItemList = mutableListOf<GuideCategoriesItem?>()

    fun setList(newList: List<GuideCategoriesItem?>?) {
        this.guideCategoriesItemList.clear()
        this.guideCategoriesItemList.addAll(newList?: emptyList())
        notifyDataSetChanged()
    }

    inner class GuideVH(val binding: GuideCategoriesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideVH {
        return GuideVH(GuideCategoriesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GuideVH, position: Int) {
        val guide=guideCategoriesItemList[position]

        holder.binding.guideItemTextviewCategoriesIcon.text = guide?.id.toString()+"."
        holder.binding.guideItemTextviewCategories.text = guide?.title.toString()

    }

    override fun getItemCount(): Int = guideCategoriesItemList.size

}