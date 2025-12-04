package com.example.rickandmortyapp.di.modules

import com.example.rickandmortyapp.data.repository.CartoonRepositoryImpl
import com.example.rickandmortyapp.domain.repository.CartoonRepository
import com.example.rickandmortyapp.domain.usecases.GetCharacterByIdUseCase
import com.example.rickandmortyapp.domain.usecases.GetCharactersUseCase
import org.koin.dsl.module


val cartoonDomainModule = module {
    single<CartoonRepository> { CartoonRepositoryImpl(api = get()) }

    single { GetCharactersUseCase(repository = get()) }
    single { GetCharacterByIdUseCase(repository = get()) }
}