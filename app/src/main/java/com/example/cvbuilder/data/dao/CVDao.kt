package com.example.cvbuilder.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cvbuilder.data.CVData
import kotlinx.coroutines.flow.Flow

@Dao
interface CVDao : BaseCVDao {
    @Query("SELECT * FROM cvdata")
    override fun getAll(): Flow<List<CVData>>

    @Query("SELECT * FROM cvdata WHERE :uid")
    override fun loadByIds(uid: Int): Flow<CVData>

    @Insert
    override fun insert(vararg cv: CVData)

    @Delete
    override fun delete(user: CVData)
}