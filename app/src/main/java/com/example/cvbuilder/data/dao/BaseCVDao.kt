package com.example.cvbuilder.data.dao

import com.example.cvbuilder.data.CVData
import kotlinx.coroutines.flow.Flow

interface BaseCVDao {
    fun getAll(): Flow<List<CVData>>
    fun loadByIds(uid: Int): Flow<CVData>
    suspend fun insert(vararg cv: CVData)
    suspend fun delete(vararg cv: CVData)
}