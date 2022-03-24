package com.example.cvbuilder.viewmodel

import com.example.cvbuilder.data.dao.BaseCVDao
import com.example.cvbuilder.repository.CVRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock

class CVViewModelTest {

    @Mock
    lateinit var repository : CVRepository
    @Mock
    lateinit var dao : BaseCVDao
    lateinit var cvViewModel: CVViewModel

    @Before
    fun setUp() {
        dao = mock()
        repository = mock()
        cvViewModel = CVViewModel(repository)
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
}