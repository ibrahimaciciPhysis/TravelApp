package com.ibrahim.travelapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ibrahim.travelapp.data.db.Database
import com.ibrahim.travelapp.data.db.entity.TravelEntity
import com.ibrahim.travelapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :Fragment() {

    private lateinit var fragmentDetailBinding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs ()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return fragmentDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentDetailBinding.detailButtonAddBookmark.setOnClickListener {

            val travel = TravelEntity(travelId = detailViewModel.plannedTravelItem.value?.id.toString(), isBookmark = true, title = detailViewModel.plannedTravelItem.value?.title.toString(), country = detailViewModel.plannedTravelItem.value?.country.toString(), city = detailViewModel.plannedTravelItem.value?.city.toString(), imageurl = detailViewModel.plannedTravelItemImage.value.toString(), description = detailViewModel.plannedTravelItem.value?.description.toString() )
            Database.getDatabase(applicationContext =requireContext()).traveldao().insertTravel(travel)
            activity?.onBackPressed()


        }
        initArgs()
        observeTravel()
    }
    private fun initArgs(){
        detailViewModel.plannedTravelItem.value = args.travel
        detailViewModel.plannedTravelItemImage.value = args.imageUrl
    }
    private fun observeTravel() {
        detailViewModel.plannedTravelItem.observe(viewLifecycleOwner){travel->
            fragmentDetailBinding.detailTextviewTitle.text = travel?.title.toString()
            fragmentDetailBinding.detailTextviewDescription.text = travel?.description.toString()
            fragmentDetailBinding.detailTextviewCity.text = travel?.city.toString()+","
            fragmentDetailBinding.detailTextviewCountry.text = travel?.country.toString()
        }
        detailViewModel.plannedTravelItemImage.observe(viewLifecycleOwner){url->
            Glide.with(fragmentDetailBinding.detailImageviewImage.context)
                .load(url)
                .into(fragmentDetailBinding.detailImageviewImage)
        }

    }
}