package com.example.rickandmortyapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.ui.utils.UIState
import com.example.rickandmortyapp.databinding.FragmentCartoonBinding
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.ui.adapter.CartoonAdapter
import com.example.rickandmortyapp.ui.base.BaseFragment
import com.example.rickandmortyapp.ui.main.detail.DetailFragment
import com.example.rickandmortyapp.ui.viewmodel.CartoonViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class CartoonFragment : BaseFragment() {
    private lateinit var binding: FragmentCartoonBinding

    private val viewModel: CartoonViewModel by viewModel()
    private lateinit var adapter: CartoonAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartoonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireActivity(), "это фрагмент", Toast.LENGTH_SHORT).show()
        collectData()
        initAdapter()
        viewModel.getCharacters()
    }

    private fun initAdapter() {
        adapter = CartoonAdapter { id ->
            openDetailFragment(id)

        }
//        binding.rvCartoon.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvCartoon.adapter = adapter
    }

    private fun collectData() {
        viewModel.charactersState.handleState(
            onSuccess = { data ->
                displayResult(data) },
            onLoading = { isLoading ->
                binding.progressBar.isVisible = isLoading
            }
        )
    }


    private fun displayResult(data: List<Character>) {
        adapter.submitList(data)
    }

    private fun openDetailFragment(id: Int) {
        val fragment = DetailFragment()

        // если нужны аргументы:
        fragment.arguments = bundleOf(
            "id" to id
//            "alive_status" to character.status,
//            "type" to character.type
        )

        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}