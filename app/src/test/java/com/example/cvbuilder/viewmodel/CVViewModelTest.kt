package com.example.cvbuilder.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.data.dao.BaseCVDao
import com.example.cvbuilder.repository.CVRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.util.*

class CVViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()
    @Mock
    lateinit var repository : CVRepository
    @Mock
    lateinit var dao : BaseCVDao
    @Mock
    private lateinit var cvViewModel: CVViewModel
    @Mock
    private lateinit var cv : Flow<List<CVData>>

    @Before
    fun setUp() {
        dao = mock()
        repository = mock()
        cvViewModel = CVViewModel(repository)
            cv = flow<List<CVData>>{
                listOf(CVData(
                0,
                "",
                "",
                "John",
                "",
                "",
                "",
                "",
                0,
                null,
                null,
                null,
                null)
            )
        }
        Mockito.`when`(repository.getAll()).thenReturn(cv)
    }

    @Test
    fun `Unique Id must not same`() {
        val uid1 = cvViewModel.generateUniqueId()
        val uid2 = cvViewModel.generateUniqueId()
        Assert.assertNotSame(uid1, uid2)
    }

    @Test
    fun `isNew return true`() {
        Assert.assertTrue(cvViewModel.isNew)
    }

    @Test
    fun `set isNew to false`() {
        cvViewModel.isNew = false
        Assert.assertFalse(cvViewModel.isNew)
    }

    @Test
    fun `getAll not null`() = runBlocking {
        val result = cvViewModel.getAll()
        Assert.assertNotNull(result)
    }
}