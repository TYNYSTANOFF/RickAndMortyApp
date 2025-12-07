package com.example.rickandmortyapp.di.modules

import com.example.rickandmortyapp.ui.viewmodel.CartoonViewModel
import com.example.rickandmortyapp.ui.viewmodel.DetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val cartoonUIModule = module {
    viewModel {
        CartoonViewModel(
            repository = get()
        )

    }
    viewModel {
        DetailViewModel(
            getCharacterByIdUseCase = get()
        )
    }
}