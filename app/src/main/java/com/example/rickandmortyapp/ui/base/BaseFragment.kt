package com.example.rickandmortyapp.ui.base

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rickandmortyapp.ui.utils.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


abstract class BaseFragment : Fragment() {


    protected fun <T> StateFlow<UIState<T>>.handleState(
//        viewLifecycleOwner: LifecycleOwner,
//        context: Context,
        onSuccess: (data: T) -> Unit,
        onLoading: (isLoading: Boolean) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@handleState.collect { state ->
                    onLoading(state is UIState.Loading)
//                    onLoading(if (state is UIState.Loading) true else false)
                    when (state) {
                        UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireActivity(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        UIState.Loading -> {}
                        is UIState.Success -> {
                            onSuccess(state.data)
                        }

                    }
                }
            }
        }
    }
}

