package com.example.rickandmortyapp.di.modules

import com.example.rickandmortyapp.ui.viewmodel.CountViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val countUIModule = module {
    viewModel {
        CountViewModel(
            incrementUseCase = get(),
            decrementUseCase = get(),
            resetCountUseCase = get(),
            getCountUseCase = get()
        )
    }
//    viewModelOf(::CountViewModel)  второй способ
}