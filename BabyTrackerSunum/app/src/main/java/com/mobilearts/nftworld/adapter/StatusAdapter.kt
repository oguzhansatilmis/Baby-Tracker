package com.mobilearts.nftworld.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.model.StatusDataClass
import com.mobilearts.nftworld.databinding.RowBinding

class StatusAdapter(

    var data: MutableList<StatusDataClass>,
) : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(data[position].feedingNote != null)
        {
            holder.binding.statusInfo.text = "Feeding"
            holder.binding.statusToday.text = data[position].today
            holder.binding.statusTime.text = data[position].hour
            holder.binding.statusImageView.setImageResource(R.drawable.wetblue)
        }
        if(data[position].diaperNote != null)
        {
            holder.binding.statusInfo.text = "Diaper"
            holder.binding.statusToday.text = data[position].today
            holder.binding.statusTime.text = data[position].hour
            holder.binding.statusImageView.setImageResource(R.drawable.diaper_recycler)

        }
        if(data[position].sleepNote != null)
        {
            holder.binding.statusInfo.text = "Sleep"
            holder.binding.statusToday.text = data[position].today
            holder.binding.statusTime.text = data[position].hour
            holder.binding.statusImageView.setImageResource(R.drawable.sleep_recycler)
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }
}