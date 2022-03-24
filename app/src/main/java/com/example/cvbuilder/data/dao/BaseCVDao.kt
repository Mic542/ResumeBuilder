package com.example.cvbuilder.data.dao

import com.example.cvbuilder.data.CVData
import kotlinx.coroutines.flow.Flow

interface BaseCVDao {
    fun getAll(): Flow<List<CVData>>
    fun loadByIds(uid: Int): Flow<CVData>
    fun insert(vararg cv: CVData)
    fun delete(user: CVData)
}