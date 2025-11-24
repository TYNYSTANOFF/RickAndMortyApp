package com.example.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.domain.models.Count
import com.example.rickandmortyapp.domain.usecases.DecrementUseCase
import com.example.counterca.domain.usecases.GetCountUseCase
import com.example.rickandmortyapp.domain.usecases.IncrementUseCase
import com.example.rickandmortyapp.domain.usecases.ResetCountUseCase

class CountViewModel(
    private val incrementUseCase: IncrementUseCase,
    private val decrementUseCase: DecrementUseCase,
    private val resetCountUseCase: ResetCountUseCase,
    private val getCountUseCase: GetCountUseCase
) : ViewModel(){
    private val _countData = MutableLiveData<Count>()
    val countData: LiveData<Count> = _countData

    fun increment() {
        incrementUseCase.increment()
        getCount()
    }

    fun decrement() {
        decrementUseCase.decrement()
        getCount()
    }

    fun resetCount() {
        resetCountUseCase.resetCount()
        getCount()
    }

    private fun getCount() {
        val response = getCountUseCase.getCount()
        _countData.value = response
    }
}