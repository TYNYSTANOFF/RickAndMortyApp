package com.example.rickandmortyapp.ui.adapter

import android.R.attr.onClick
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ItemCartoonBinding
import com.example.rickandmortyapp.domain.models.Character

class CartoonAdapter(
    private val onClick: (Int) -> Unit
) : PagingDataAdapter<Character, CartoonAdapter.CartoonViewHolder>(diffCallback = CharacterDiffUtil) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartoonViewHolder {
        val binding = ItemCartoonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartoonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CartoonViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
//            onClick(getItem(position).id)
            getItem(position)?.let { it1 -> onClick(it1.id) }
        }
    }


    inner class CartoonViewHolder(private val binding: ItemCartoonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character?) = with(binding) {
            character?.let { character ->
                ivAvatar.load(character.image) {
                    crossfade(true)
//                placeholder(R.drawable.img_placeholder)
//                error(R.drawable.img_errorimage)
                }
                tvName.text = character.name
                tvFirstSeen.text = character.origin.name
                tvLastLocation.text = character.location.name
//                tvStatusAndType.text = "${character.status} - ${character.species}"
//                раньше было unknown с маленькой буквы и мне это не нравилось и решил поменять на заглавную

                val status = when (character.status) {
                    "unknown" -> tvStatusAndType.text = "Unknown - ${character.species}"
                    else -> tvStatusAndType.text = "${character.status} - ${character.species}"
                }

                val color = when (character.status) {
                    "Alive" -> R.color.green
                    "Dead" -> R.color.red
                    else -> R.color.light_grey
                }
                binding.ivAliveStatus.setColorFilter(
                    ContextCompat.getColor(binding.root.context, color),
                    PorterDuff.Mode.SRC_IN
                )
            }




        }
    }
}

object CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(
        oldItem: Character,
        newItem: Character
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Character,
        newItem: Character
    ): Boolean {
        return oldItem == newItem
    }

}