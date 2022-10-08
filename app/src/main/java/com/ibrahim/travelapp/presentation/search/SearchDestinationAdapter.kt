package com.ibrahim.travelapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.travelapp.databinding.SearchDestinationsItemBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem

class SearchDestinationAdapter() : RecyclerView.Adapter<SearchDestinationAdapter.SearchVH>() {

    private var allTravelItemList = mutableListOf<AllTravelItem?>()

    fun setList(newList: List<AllTravelItem?>?) {
        this.allTravelItemList.clear()
        this.allTravelItemList.addAll(newList?: emptyList())
        notifyDataSetChanged()
    }

    inner class SearchVH(val binding: SearchDestinationsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        return SearchVH(SearchDestinationsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        val travel=allTravelItemList[position]

        Glide.with(holder.binding.searchItemImageviewDestination)
            .load(travel?.images?.get(1)?.url.toString())
            .into(holder.binding.searchItemImageviewDestination)

        holder.binding.searchItemTextviewCity.text = travel?.city.toString()
        holder.binding.searchItemTextviewCountry.text = travel?.country.toString()

        holder.binding.root.setOnClickListener {
            val imageUrl = travel?.images?.get(1)?.url.toString()
            val action= SearchFragmentDirections.actionSearchFragmentToDetailFragment(travel,imageUrl)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int = allTravelItemList.size

}