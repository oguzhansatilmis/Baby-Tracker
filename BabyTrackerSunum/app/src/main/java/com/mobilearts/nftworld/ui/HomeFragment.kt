package com.mobilearts.nftworld.ui

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.databinding.FragmentHomeBinding
import com.mobilearts.nftworld.viewmodel.Viewmodel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewmodel: Viewmodel
     var deger: ByteArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.viewModelScope.launch {
            println("data"+viewmodel.getData())


            viewmodel.getData().forEach {

                binding.babyName.text =it.babyFullName
                val dateString = it.birthDate
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy") // biçimlendiriciyi tanımla
                val date = LocalDate.parse(dateString, formatter) // String değerini LocalDate'a dönüştür

                var now = LocalDate.now()

                val months = ChronoUnit.MONTHS.between(date, now)
                val days = ChronoUnit.DAYS.between(date.plusMonths(months), now)

                binding.babyMonths.text = "$months Mounths $days Days"
                println("Verilen tarihten bugüne kadar geçen süre: $months ay $days gün")
                deger = it.babyProfileImage
                if(deger ==null){

                   binding.imageView7.setImageResource(R.drawable.btn_addphoto)
                }
                if(deger !=null){
                    var bitmap = deger?.let { it1 -> BitmapFactory.decodeByteArray(deger, 0, it1.size)
                    }
                    binding.imageView7.setImageBitmap(bitmap)
                }
            }
        }
        binding.imageviewFeeding.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFeedingFragment())
        }
        binding.imageviewDiaperchange.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDiaperFragment())
        }
        binding.imageviewSleep.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSleepFragment())
        }
        binding.homeCalender.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCalenderFragment())
        }
        binding.babyEditProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
        binding.homeSettings.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettFragment())
        }
    }

}


