package com.example.cvbuilder.viewmodel

import androidx.lifecycle.*
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.repository.CVRepositoryImpl
import kotlinx.coroutines.launch

class MainViewModel(private val cvRepository: CVRepositoryImpl) : ViewModel() {
    val allCVData: LiveData<List<CVData>> = cvRepository.getAll().asLiveData()

    fun insert(cv: CVData) = viewModelScope.launch {
        cvRepository.insert(cv)
    }
}