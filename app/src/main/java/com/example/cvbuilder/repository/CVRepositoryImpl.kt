package com.example.cvbuilder.repository

import androidx.annotation.WorkerThread
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.data.dao.BaseCVDao
import com.example.cvbuilder.data.dao.CVDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CVRepositoryImpl @Inject constructor(private val cvDao: BaseCVDao) : CVRepository {

    @WorkerThread
    override fun insert(cv: CVData) {
        cvDao.insert(cv)
    }

    override fun getAll() : Flow<List<CVData>> {
        return cvDao.getAll()
    }

}