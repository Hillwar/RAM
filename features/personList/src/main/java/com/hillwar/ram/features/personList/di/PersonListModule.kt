package com.hillwar.ram.features.personList.di

import com.hillwar.ram.core.network.RequestHandler
import com.hillwar.ram.core.network.RickAndMortyService
import com.hillwar.ram.features.personList.data.PersonListRepository
import com.hillwar.ram.features.personList.data.PersonListRepositoryImpl
import com.hillwar.ram.features.personList.domain.PersonListInteractor
import com.hillwar.ram.features.personList.domain.PersonListInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonListModule {

    @Provides
    @Singleton
    fun providePersonListRepository(
        service: RickAndMortyService,
        requestHandler: RequestHandler
    ): PersonListRepository = PersonListRepositoryImpl(service, requestHandler)

    @Provides
    @Singleton
    fun providePersonListInteractor(
        repository: PersonListRepository
    ): PersonListInteractor = PersonListInteractorImpl(repository)
}