package com.mobilearts.nftworld.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilearts.nftworld.model.BabyDataClass
import com.mobilearts.nftworld.database.BabyDatabase
import com.mobilearts.nftworld.repository.Repository
import com.mobilearts.nftworld.model.StatusDataClass
import com.mobilearts.nftworld.database.StatusDatabase
import kotlinx.coroutines.launch

class Viewmodel (application: Application): AndroidViewModel(application){
    private val repository : Repository

    init{
        val babyDao = BabyDatabase.getDatabase(application).babyDao()
        val statusDao = StatusDatabase.getInstance(application).statusDao()
        repository = Repository(babyDao,statusDao)
    }

    suspend fun getData() = repository.getBaby()
    suspend fun getStatus() = repository.getStatus()

    fun insertBaby(babyDataClass: BabyDataClass) = viewModelScope.launch {
        repository.insert(babyDataClass)
    }

    fun insertSleep(statusDataClass: StatusDataClass) = viewModelScope.launch {
        repository.insertStatus(statusDataClass)
    }
}