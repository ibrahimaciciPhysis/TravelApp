package com.ibrahim.travelapp.presentation.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.travelapp.databinding.GuideArticlesItemBinding
import com.ibrahim.travelapp.databinding.GuideMightItemBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem
import com.ibrahim.travelapp.presentation.home.HomeFragmentDirections

class GuideArticleAdapter() : RecyclerView.Adapter<GuideArticleAdapter.GuideVH>() {

    private var allTravelItemList = mutableListOf<AllTravelItem?>()

    fun setList(newList: List<AllTravelItem?>?) {
        this.allTravelItemList.clear()
        this.allTravelItemList.addAll(newList?: emptyList())
        notifyDataSetChanged()
    }

    inner class GuideVH(val binding: GuideArticlesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideVH {
        return GuideVH(GuideArticlesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GuideVH, position: Int) {
        val travel=allTravelItemList[position]

        Glide.with(holder.binding.guideItemImageviewArticle)
            .load(travel?.images?.get(0)?.url.toString())
            .into(holder.binding.guideItemImageviewArticle)

        holder.binding.guideItemTextviewCategories.text = travel?.category.toString()
        holder.binding.guideItemTextviewDescription.text = travel?.description.toString()

        holder.binding.root.setOnClickListener {
            val imageUrl = travel?.images?.get(0)?.url.toString()
            val action= GuideFragmentDirections.actionGuideFragmentToDetailFragment(travel,imageUrl)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int = allTravelItemList.size

}