package com.mobilearts.nftworld.ui.status

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentFeedingBinding
import com.mobilearts.nftworld.viewmodel.Viewmodel
import com.mobilearts.nftworld.model.StatusDataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FeedingFragment : Fragment() {
    private lateinit var binding: FragmentFeedingBinding
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
       binding = FragmentFeedingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.feedingTimeEditeTextView.setOnClickListener {
            showTimePickerDialog(binding.feedingTimeEditeText)

        }

        binding.feedingNote.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    // update view
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    // do nothing
                }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val deger = binding.amountEditText.text.toString().replace(" ml", "")
                binding.amountEditText.setText("$deger ml")
            }
            })

        binding.nextButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                saveFeedingData()
                println("feeding  kaydedildi")

            }
            findNavController().navigate(FeedingFragmentDirections.actionFeedingFragmentToHomeFragment())
        }
        binding.feedingBackButton.setOnClickListener {
            findNavController().navigate(FeedingFragmentDirections.actionFeedingFragmentToHomeFragment())
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

    fun saveFeedingData() {
        val feedingTime = binding.feedingTimeEditeText.text.toString()
        val feedingAmount = binding.amountEditText.text.toString()
        val feedingNote = binding.feedingNote.text.toString()

        date()
        hour()

        val diaper = StatusDataClass(feedingTime = feedingTime, feedingAmountMl = feedingAmount, feedingNote = feedingNote, today = date(), hour = hour())
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