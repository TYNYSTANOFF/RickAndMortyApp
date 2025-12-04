package com.example.rickandmortyapp.ui.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.FragmentDetailBinding
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.ui.base.BaseFragment
import com.example.rickandmortyapp.ui.viewmodel.CartoonViewModel
import com.example.rickandmortyapp.ui.viewmodel.DetailViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue


class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        collectData()
        viewModel.getCharacterById(id)
    }

    private fun collectData() {
        viewModel.charactersState.handleState(
            onSuccess = { data ->
                displayResult(data) },
            onLoading = { isLoading ->
                binding.progressBar.isVisible = isLoading })
    }

    private fun displayResult(character: Character?) {
        with(binding) {
            tvName.text = character?.name
            ivAvatar.load(character?.image) {
                crossfade(true)
//                placeholder(R.drawable.img_placeholder)
            }
            tvStatusAndType.text = "${character?.status} - ${character?.species}"
            tvGender.text = character?.gender
            tvLastLocation.text = character?.location?.name
            tvFirstSeen.text = character?.location?.name
            tvSeasonOne.text = character?.origin?.name
            tvEpisodeOne.text = character?.created

        }
    }
}