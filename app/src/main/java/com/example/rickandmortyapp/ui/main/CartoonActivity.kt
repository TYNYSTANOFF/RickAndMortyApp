package com.example.rickandmortyapp.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityCartoonBinding
import com.example.rickandmortyapp.ui.viewmodel.CountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CartoonActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCartoonBinding.inflate(layoutInflater)
    }

//    private val viewModel: CountViewModel by lazy {
//        ViewModelProvider(this)[CountViewModel::class.java]
//    }

    private val viewModel: CountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        with(binding) {
            btnIncrement.setOnClickListener {
                viewModel.increment()
            }
            btnDescrement.setOnClickListener {
                viewModel.decrement()
            }
            btnReset.setOnClickListener {
                viewModel.resetCount()
            }

            viewModel.countData.observe(this@CartoonActivity) { countData ->
                binding.apply {
                    tvCount.text = countData.count.toString()
                    tvTypeOfOperation.text = countData.typeOfOperation.value
                    tvTime.text = formatMillisToDateTime(countData.createdAt)
                }

            }

        }
    }

    fun formatMillisToDateTime(
        milliseconds: Long,
        pattern: String = "yyyy-MM-dd HH:mm:ss"
    ): String {
        val date = Date(milliseconds)
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(date)
    }
}