package com.example.cvbuilder.module

import com.example.cvbuilder.repository.CVRepository
import com.example.cvbuilder.repository.CVRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CVRepositoryModule {
    @Binds
    abstract fun bindCVRepository(
        cvRepositoryImpl: CVRepositoryImpl
    ): CVRepository
}