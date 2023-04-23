package com.mobilearts.nftworld.repository

import com.mobilearts.nftworld.data.BabyDao
import com.mobilearts.nftworld.data.StatusDao
import com.mobilearts.nftworld.model.BabyDataClass
import com.mobilearts.nftworld.model.StatusDataClass

class Repository(private val babyDao: BabyDao, private val statusDao: StatusDao) {

    suspend fun getBaby() = babyDao.getBaby()
    suspend fun getStatus() = statusDao.getStatus()

    suspend fun insert(babyDataClass: BabyDataClass){
        babyDao.insertBaby(babyDataClass)
    }
    suspend fun insertStatus(statusDataClass: StatusDataClass){
        statusDao.insertStatus(statusDataClass)
    }
}