package com.mobilearts.nftworld.ui.status

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentSleepBinding
import com.mobilearts.nftworld.viewmodel.Viewmodel
import com.mobilearts.nftworld.model.StatusDataClass
import java.text.SimpleDateFormat
import java.util.*


class SleepFragment : Fragment() {
    private lateinit var binding: FragmentSleepBinding
    private val calendar = Calendar.getInstance()
    private lateinit var viewmodel: Viewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSleepBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fellSleepView.setOnClickListener {
            showTimePickerDialog(binding.fellSleepTextview)

        }
        binding.wakeupView.setOnClickListener {
            showTimePickerDialog(binding.wakeupTextview)

        }

        binding.nextButton.setOnClickListener {

            println("sleep kaydedildi")
            saveSleepData()
            findNavController().navigate(SleepFragmentDirections.actionSleepFragmentToHomeFragment())
        }
        binding.sleepBackButton.setOnClickListener {
            findNavController().navigate(SleepFragmentDirections.actionSleepFragmentToHomeFragment())
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
    fun saveSleepData() {
        val fellSleep = binding.fellSleepTextview.text.toString()
        val  wakeUpSleepTime= binding.wakeupTextview.text.toString()
        val sleepNote = binding.sleepNote.text.toString()

         val diaper =
             StatusDataClass(feelSleep = fellSleep, wakeupSleep = wakeUpSleepTime,sleepNote = sleepNote, today = date(), hour = hour())
        viewmodel.insertSleep(diaper)

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