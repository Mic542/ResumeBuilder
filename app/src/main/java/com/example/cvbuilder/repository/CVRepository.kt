package com.example.cvbuilder.repository

import com.example.cvbuilder.data.CVData
import kotlinx.coroutines.flow.Flow

interface CVRepository {
    fun insert(cv : CVData)
    fun getAll() : Flow<List<CVData>>
}