package com.example.rickandmortyapp.di.modules

import com.example.rickandmortyapp.data.datasource.CountApi
import org.koin.dsl.module

val countDataModule = module {
    //data
    single { CountApi() }
}
