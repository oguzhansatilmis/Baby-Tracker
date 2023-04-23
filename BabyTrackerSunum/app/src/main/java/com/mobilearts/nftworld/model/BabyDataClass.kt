package com.mobilearts.nftworld.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time


@Entity(tableName = "baby")
data class BabyDataClass(
    @PrimaryKey(autoGenerate = false)
    val babyid : Int = 0,
    val babyFullName :String,
    val birthDate: String,
    val timeofBirth :String,
    val dueDate :String,
    val babyGender :String,
    val babyProfileImage :ByteArray,
)






