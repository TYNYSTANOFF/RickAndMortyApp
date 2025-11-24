package com.example.rickandmortyapp

import android.app.Application
import com.example.rickandmortyapp.di.modules.countDataModule
import com.example.rickandmortyapp.di.modules.countDomainModule
import com.example.rickandmortyapp.di.modules.countUIModule
import com.example.rickandmortyapp.di.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CartoonApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CartoonApplication)
            modules(countDataModule, countDomainModule, countUIModule, networkModule)
        }
    }
}