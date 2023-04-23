package com.mobilearts.nftworld.ui.status

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.adapter.DiaperAdapter
import com.mobilearts.nftworld.adapter.FeedingAdapter
import com.mobilearts.nftworld.adapter.SleepAdapter
import com.mobilearts.nftworld.adapter.StatusAdapter
import com.mobilearts.nftworld.model.StatusDataClass
import com.mobilearts.nftworld.databinding.FragmentCalenderBinding
import com.mobilearts.nftworld.viewmodel.Viewmodel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CalenderFragment : Fragment() {
    private lateinit var binding: FragmentCalenderBinding
    private lateinit var viewmodel: Viewmodel
    private var list: MutableList<StatusDataClass> = mutableListOf()
    private lateinit var adapter: StatusAdapter
    private lateinit var adapter1: FeedingAdapter
    private lateinit var adapter2: DiaperAdapter
    private lateinit var adapter3: SleepAdapter

    var listeFeeding: MutableList<String> = mutableListOf()
    var listeDiaper: MutableList<String> = mutableListOf()
    var listeSleep: MutableList<String> = mutableListOf()

    var listeFeedingToday: MutableList<String> = mutableListOf()
    var listeDiaperToday: MutableList<String> = mutableListOf()
    var listeSleepToday: MutableList<String> = mutableListOf()

    var listeFeedingHour: MutableList<String> = mutableListOf()
    var listeDiaperHour: MutableList<String> = mutableListOf()
    var listeSleepHour: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]
        adapter = StatusAdapter(list)
        adapter1 = FeedingAdapter(listeFeeding,listeFeedingToday,listeFeedingHour)
        adapter2 = DiaperAdapter(listeDiaper,listeDiaperToday,listeSleepHour)
        adapter3 = SleepAdapter(listeSleep,listeSleepToday,listeSleepHour)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCalenderBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        imageviewAll()
        getAllAdapter()
        binding.dateCalander.text = date()
        adapter.notifyDataSetChanged()

        binding.allStatus.setOnClickListener {
            imageviewAll()
            getAllAdapter()
        }
        binding.feedingStatus.setOnClickListener {
            imageviewFeeding()
            getFeedingAdapter()
        }
        binding.diaperStatus.setOnClickListener {
            imageviewDiaper()
            getDiaperAdapter()
        }
        binding.sleepStatus.setOnClickListener {
            imageviewSleep()
            getSleepAdapter()
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(CalenderFragmentDirections.actionCalenderFragmentToHomeFragment())
        }

    }

    private fun getAllAdapter()
    {
        listeCek()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }
    private fun getFeedingAdapter()
    {
        listeCek()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter1
        adapter1.notifyDataSetChanged()


    }
    private fun getDiaperAdapter()
    {
        listeCek()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter2
        adapter2.notifyDataSetChanged()

    }
    private fun getSleepAdapter()
    {
        listeCek()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter3
        adapter3.notifyDataSetChanged()

    }
    private fun  imageviewAll()
    {
        binding.allStatus.setImageResource(R.drawable.text_all_selected)
        binding.feedingStatus.setImageResource(R.drawable.icon_feeding_unselected)
        binding.diaperStatus.setImageResource(R.drawable.icon_diaper_unselected)
        binding.sleepStatus.setImageResource(R.drawable.icon_sleep_unselected)
    }
    private fun  imageviewFeeding()
    {
        binding.allStatus.setImageResource(R.drawable.text_all_unselected)
        binding.feedingStatus.setImageResource(R.drawable.feeding_selected)
        binding.diaperStatus.setImageResource(R.drawable.icon_diaper_unselected)
        binding.sleepStatus.setImageResource(R.drawable.icon_sleep_unselected)
    }
    private fun  imageviewDiaper()
    {
        binding.allStatus.setImageResource(R.drawable.text_all_unselected)
        binding.feedingStatus.setImageResource(R.drawable.icon_feeding_unselected)
        binding.diaperStatus.setImageResource(R.drawable.diaper_selecct)
        binding.sleepStatus.setImageResource(R.drawable.icon_sleep_unselected)
    }
    private fun  imageviewSleep()
    {
        binding.allStatus.setImageResource(R.drawable.text_all_unselected)
        binding.feedingStatus.setImageResource(R.drawable.icon_feeding_unselected)
        binding.diaperStatus.setImageResource(R.drawable.icon_diaper_unselected)
        binding.sleepStatus.setImageResource(R.drawable.sleep_select)
    }
    private fun listeCek()
    {
        viewmodel.viewModelScope.launch {
            list.clear()

            listeFeeding.clear()
            listeDiaper.clear()
            listeSleep.clear()

            viewmodel.getStatus().forEach{
                list.addAll(listOf(it))
              if(it.feedingNote !=null)
                {
                    listeFeeding.add(it.feedingNote)
                    listeFeedingToday.add(it.today)
                    listeFeedingHour.add(it.hour)

                }
                if(it.diaperNote !=null)
                {
                    listeDiaper.add(it.diaperNote)
                    listeDiaperToday.add(it.today)
                    listeDiaperHour.add(it.hour)
                }
                if(it.sleepNote !=null)
                {
                    listeSleep.add(it.sleepNote)
                    listeSleepToday.add(it.today)
                    listeSleepHour.add(it.hour)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
    fun date() :String {
        val dateFormat = SimpleDateFormat("dd,MM EEEE")
        val currentDate = Date()
        val today = dateFormat.format(currentDate)
        println(today)
        return today

    }
}