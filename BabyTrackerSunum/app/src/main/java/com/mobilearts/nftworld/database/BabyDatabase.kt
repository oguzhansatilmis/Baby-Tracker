package com.mobilearts.nftworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobilearts.nftworld.data.BabyDao
import com.mobilearts.nftworld.model.BabyDataClass

@Database(entities = [BabyDataClass::class], version = 1)
abstract class BabyDatabase :RoomDatabase() {

    abstract fun babyDao() : BabyDao

    companion object{
        @Volatile
        private var instance : BabyDatabase? =null

        fun getDatabase(contex : Context) : BabyDatabase {
            return instance ?: synchronized(this){
                val database = Room.databaseBuilder(
                    contex.applicationContext,
                    BabyDatabase::class.java,
                    "baby_database"
                ).build()

                instance = database
                database
            }
        }
    }

}