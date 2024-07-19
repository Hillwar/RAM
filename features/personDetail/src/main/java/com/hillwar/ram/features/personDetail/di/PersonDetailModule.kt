package com.hillwar.ram.features.personDetail.di

import com.hillwar.ram.core.network.RequestHandler
import com.hillwar.ram.core.network.RickAndMortyService
import com.hillwar.ram.features.personDetail.data.PersonDetailRepository
import com.hillwar.ram.features.personDetail.data.PersonDetailRepositoryImpl
import com.hillwar.ram.features.personDetail.domain.PersonDetailInteractor
import com.hillwar.ram.features.personDetail.domain.PersonDetailInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonDetailModule {

    @Provides
    @Singleton
    fun providePersonDetailRepository(
        service: RickAndMortyService,
        requestHandler: RequestHandler
    ): PersonDetailRepository = PersonDetailRepositoryImpl(service, requestHandler)

    @Provides
    @Singleton
    fun providePersonDetailInteractor(
        repository: PersonDetailRepository
    ): PersonDetailInteractor = PersonDetailInteractorImpl(repository)
}
