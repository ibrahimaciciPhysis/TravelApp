package com.ibrahim.travelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.travelapp.databinding.HomeDealsItemBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.HomeVH>() {

    private var allTravelItemList = mutableListOf<AllTravelItem?>()

    fun setList(newList: List<AllTravelItem?>?) {
        this.allTravelItemList.clear()
        this.allTravelItemList.addAll(newList?: emptyList())
        notifyDataSetChanged()
    }

    inner class HomeVH(val binding: HomeDealsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        return HomeVH(HomeDealsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        val travel=allTravelItemList[position]

        Glide.with(holder.binding.homeItemImageview.context)
            .load(travel?.images?.get(0)?.url.toString())
            .into(holder.binding.homeItemImageview)

        holder.binding.root.setOnClickListener {
            val imageUrl = travel?.images?.get(0)?.url.toString()
            val action= HomeFragmentDirections.actionHomeFragmentToDetailFragment(travel,imageUrl)
            Navigation.findNavController(it).navigate(action)
        }

    }



    override fun getItemCount(): Int = allTravelItemList.size

}