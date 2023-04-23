package com.mobilearts.nftworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobilearts.nftworld.data.StatusDao
import com.mobilearts.nftworld.model.StatusDataClass

@Database(entities = [StatusDataClass::class], version = 1)
abstract class StatusDatabase : RoomDatabase() {
    abstract fun statusDao(): StatusDao

    companion object {
        @Volatile
        private var INSTANCE: StatusDatabase? = null

        fun getInstance(context: Context): StatusDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StatusDatabase::class.java,
                    "status_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
