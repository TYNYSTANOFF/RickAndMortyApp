package com.example.rickandmortyapp.di.modules

import com.example.rickandmortyapp.data.datasource.CartoonApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { get<Retrofit>().create(CartoonApi::class.java) }

    single { provideRetrofit(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .connectTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .readTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .writeTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .callTimeout(timeout = 30, unit = TimeUnit.SECONDS)
        .build()
}