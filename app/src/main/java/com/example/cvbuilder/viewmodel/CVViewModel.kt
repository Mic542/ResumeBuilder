package com.example.cvbuilder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.repository.CVRepository
import com.example.cvbuilder.repository.CVRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CVViewModel @Inject constructor(private val cvRepository: CVRepository) : ViewModel() {
    var isNew : Boolean = true
    lateinit var currentCVData : CVData

    fun getAll() : LiveData<List<CVData>> {
        return cvRepository.getAll().asLiveData()
    }

    fun insert(cv: CVData) = viewModelScope.launch {
        cvRepository.insert(cv)
    }

    fun delete(cv: CVData) = viewModelScope.launch {
        cvRepository.delete(cv)
    }

    fun generateUniqueId() : Int = ((Date().getTime() / 1000L) % Integer.MAX_VALUE).toInt()

}