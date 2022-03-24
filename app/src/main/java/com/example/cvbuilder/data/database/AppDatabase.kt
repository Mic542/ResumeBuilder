package com.example.cvbuilder.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.data.dao.CVDao
import com.example.cvbuilder.utils.Utils

@Database(entities = [CVData::class], version = 1)
@TypeConverters(Utils::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cvDao(): CVDao

    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "word_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
    }
}