package com.example.rickandmortyapp

import android.app.Application
import com.example.rickandmortyapp.di.modules.cartoonDomainModule
import com.example.rickandmortyapp.di.modules.cartoonUIModule
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
            modules(networkModule, cartoonDomainModule, cartoonUIModule)
        }
    }
}