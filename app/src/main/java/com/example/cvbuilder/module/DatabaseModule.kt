package com.example.cvbuilder.module

import android.content.Context
import androidx.room.Room
import com.example.cvbuilder.data.dao.BaseCVDao
import com.example.cvbuilder.data.dao.CVDao
import com.example.cvbuilder.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @ApplicationDatabase
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        var INSTANCE: AppDatabase? = null
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cv_database"
            ).build()
            INSTANCE = instance
            // return instance
            instance
        }
    }

    @Provides
    fun provideCVDao(
        @ApplicationDatabase applicationDatabase: AppDatabase
    ) : BaseCVDao {
        return applicationDatabase.cvDao()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationDatabase