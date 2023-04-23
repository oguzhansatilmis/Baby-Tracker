package com.mobilearts.nftworld.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.databinding.RowBinding

class FeedingAdapter(
    var data: MutableList<String>,
    var today: MutableList<String>,
    var hour: MutableList<String>
) : RecyclerView.Adapter<FeedingAdapter.ViewHolder>() {

    class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.binding.statusInfo.text = "Feeding"
            holder.binding.statusToday.text = today[position]
            holder.binding.statusTime.text = hour[position]
            holder.binding.statusImageView.setImageResource(R.drawable.feeding_selected)
    }
    override fun getItemCount(): Int {

        return data.size

    }
}