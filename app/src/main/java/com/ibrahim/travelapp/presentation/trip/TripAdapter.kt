package com.ibrahim.travelapp.presentation.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibrahim.travelapp.data.db.entity.TravelEntity
import com.ibrahim.travelapp.databinding.TripPlanItemBinding
import com.ibrahim.travelapp.domain.model.alltravel.AllTravelItem

class TripAdapter() : RecyclerView.Adapter<TripAdapter.TripVH>() {

    private val plannedTravel: ArrayList<TravelEntity> = arrayListOf()

    fun setList(plannedTravel:List<TravelEntity>){
        this.plannedTravel.clear()
        this.plannedTravel.addAll(plannedTravel)
        notifyDataSetChanged()
    }

    inner class TripVH(val binding: TripPlanItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripVH {
        return TripVH(TripPlanItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TripVH, position: Int) {
        val travel=plannedTravel[position]

        Glide.with(holder.binding.tripItemImageview.context)
            .load(travel.imageurl)
            .into(holder.binding.tripItemImageview)

        holder.binding.tripItemTextviewCity.text = travel.city
        holder.binding.tripItemTextviewCountry.text = travel.country

    }



override fun getItemCount(): Int = plannedTravel.size

}