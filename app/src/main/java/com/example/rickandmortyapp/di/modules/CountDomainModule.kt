package com.example.rickandmortyapp.di.modules

import com.example.rickandmortyapp.data.repository.CounterRepositoryImpl
import com.example.rickandmortyapp.domain.repository.CounterRepository
import com.example.rickandmortyapp.domain.usecases.DecrementUseCase
import com.example.counterca.domain.usecases.GetCountUseCase
import com.example.rickandmortyapp.domain.usecases.IncrementUseCase
import com.example.rickandmortyapp.domain.usecases.ResetCountUseCase
import org.koin.dsl.module


val countDomainModule = module {
    single<CounterRepository> { CounterRepositoryImpl(api = get()) }

    single { IncrementUseCase(repository = get()) }
    single { DecrementUseCase(repository = get()) }
    single { ResetCountUseCase(repository = get()) }
    single { GetCountUseCase(repository = get()) }
}