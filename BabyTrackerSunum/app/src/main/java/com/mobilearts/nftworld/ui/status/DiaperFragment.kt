package com.mobilearts.nftworld.ui.status

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.databinding.FragmentDiaperBinding
import com.mobilearts.nftworld.viewmodel.Viewmodel
import com.mobilearts.nftworld.model.StatusDataClass
import java.text.SimpleDateFormat
import java.util.*


class DiaperFragment : Fragment(){
    private lateinit var binding: FragmentDiaperBinding
    private lateinit var diaperStatus :String
    private val calendar = Calendar.getInstance()
    private lateinit var viewModel: Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[Viewmodel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiaperBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.diaperTimeTextview.setOnClickListener {
            showTimePickerDialog(binding.diaperTimeTextview)

        }
        binding.statusWet.setOnClickListener {
            binding.statusWet.setTextColor(Color.parseColor("#4625C3"))
            binding.statusDirty.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statusMixed.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statDry.setTextColor(Color.parseColor("#C2C2C2"))


            binding.imageWet.setImageResource(R.drawable.icon_wet_selected)
            binding.imageDirty.setImageResource(R.drawable.icon_dirty_unselected)
            binding.imageMixed.setImageResource(R.drawable.icon_mixed_unselected)
            binding.imageDry.setImageResource(R.drawable.icon_dry_unselected)

            diaperStatus = "Wet"

        }

        binding.statusDirty.setOnClickListener {
            binding.statusWet.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statusDirty.setTextColor(Color.parseColor("#4625C3"))
            binding.statusMixed.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statDry.setTextColor(Color.parseColor("#C2C2C2"))


            binding.imageWet.setImageResource(R.drawable.icon_wet_unselected)
            binding.imageDirty.setImageResource(R.drawable.icon_dirty_selected)
            binding.imageMixed.setImageResource(R.drawable.icon_mixed_unselected)
            binding.imageDry.setImageResource(R.drawable.icon_dry_unselected)

            diaperStatus = "Dirty"
        }
        binding.statusMixed.setOnClickListener {

            binding.statusWet.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statusDirty.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statusMixed.setTextColor(Color.parseColor("#4625C3"))
            binding.statDry.setTextColor(Color.parseColor("#C2C2C2"))


            binding.imageWet.setImageResource(R.drawable.icon_wet_unselected)
            binding.imageDirty.setImageResource(R.drawable.icon_dirty_unselected)
            binding.imageMixed.setImageResource(R.drawable.icon_mixed_selected)
            binding.imageDry.setImageResource(R.drawable.icon_dry_unselected)


            diaperStatus = "Mixed"

        }
        binding.statDry.setOnClickListener {

            binding.statusWet.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statusDirty.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statusMixed.setTextColor(Color.parseColor("#C2C2C2"))
            binding.statDry.setTextColor(Color.parseColor("#4625C3"))


            binding.imageWet.setImageResource(R.drawable.icon_wet_unselected)
            binding.imageDirty.setImageResource(R.drawable.icon_dirty_unselected)
            binding.imageMixed.setImageResource(R.drawable.icon_mixed_unselected)
            binding.imageDry.setImageResource(R.drawable.icon_dry_selected)

            diaperStatus= "Dry"

        }
        binding.dipaerNextButton.setOnClickListener {
            saveDiaperData()
            findNavController().navigate(DiaperFragmentDirections.actionDiaperFragmentToHomeFragment())
            println("diaper kaydedildi")
        }
        binding.diaperBackButton.setOnClickListener {
            findNavController().navigate(DiaperFragmentDirections.actionDiaperFragmentToHomeFragment())
        }


    }
    private fun showTimePickerDialog(textview: TextView) {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
            textview.text=time.toString()

        }

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(requireContext(), timeSetListener, hour, minute, true).show()
    }
    fun saveDiaperData() {
        val diaperTime = binding.diaperTimeTextview.text.toString()
        val diaperNote = binding.dipaerNote.text.toString()

        val diaper = StatusDataClass(diaperTime = diaperTime, diaperNote = diaperNote, diaperStatus = diaperStatus, today = date(), hour = hour())
        viewModel.insertSleep(diaper)


    }
    fun date() :String {
        val dateFormat = SimpleDateFormat("dd,MM EEEE")
        val currentDate = Date()
        val today = dateFormat.format(currentDate)
        println(today)
        return today

    }
    fun hour() :String{
        val dateFormat = SimpleDateFormat("HH:mm")
        val currentDate = Date()
        val time = dateFormat.format(currentDate)
        println(time)
        return time
    }

}