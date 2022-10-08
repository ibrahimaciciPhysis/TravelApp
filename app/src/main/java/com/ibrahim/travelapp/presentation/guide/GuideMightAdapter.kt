package com.ibrahim.travelapp.presentation.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.travelapp.databinding.GuideMightItemBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.travelapp.presentation.home.HomeFragmentDirections

class GuideMightAdapter() : RecyclerView.Adapter<GuideMightAdapter.GuideVH>() {

    private var allTravelItemList = mutableListOf<AllTravelItem?>()

    fun setList(newList: List<AllTravelItem?>?) {
        this.allTravelItemList.clear()
        this.allTravelItemList.addAll(newList?: emptyList())
        notifyDataSetChanged()
    }

    inner class GuideVH(val binding: GuideMightItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideVH {
        return GuideVH(GuideMightItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GuideVH, position: Int) {
        val travel=allTravelItemList[position]

        Glide.with(holder.binding.guideItemImageviewMight)
            .load(travel?.images?.get(3)?.url.toString())
            .into(holder.binding.guideItemImageviewMight)

        holder.binding.root.setOnClickListener {
            val imageUrl = travel?.images?.get(3)?.url.toString()
            val action= GuideFragmentDirections.actionGuideFragmentToDetailFragment(travel,imageUrl)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int = allTravelItemList.size

}