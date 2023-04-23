package com.mobilearts.nftworld.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_data")
data class StatusDataClass(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val feedingTime: String? = null,
    val feedingAmountMl: String?= null,
    val feedingNote: String?= null,

    val diaperTime: String?= null,
    val diaperStatus: String?= null,
    val diaperNote: String?= null,

    val feelSleep: String?= null,
    val wakeupSleep: String?= null,
    val sleepNote: String?= null,

    val today :String,
    val hour :String
)
