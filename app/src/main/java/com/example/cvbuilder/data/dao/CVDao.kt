package com.example.cvbuilder.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cvbuilder.data.CVData
import kotlinx.coroutines.flow.Flow

@Dao
interface CVDao : BaseCVDao {
    @Query("SELECT * FROM cvdata")
    override fun getAll(): Flow<List<CVData>>

    @Query("SELECT * FROM cvdata WHERE :uid")
    override fun loadByIds(uid: Int): Flow<CVData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(vararg cv: CVData)

    @Delete
    override suspend fun delete(vararg cv: CVData)
}