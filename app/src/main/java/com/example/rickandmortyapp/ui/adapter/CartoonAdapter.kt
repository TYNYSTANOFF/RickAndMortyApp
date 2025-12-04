package com.example.rickandmortyapp.ui.adapter

import android.R.attr.onClick
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ItemCartoonBinding
import com.example.rickandmortyapp.domain.models.Character

class CartoonAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<CartoonAdapter.CartoonViewHolder>() {

    private val list = mutableListOf<Character>()

    fun submitList(data: List<Character>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

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
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick(list[position].id)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class CartoonViewHolder(private val binding: ItemCartoonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) = with(binding) {
//            Glide.with(ivAvatar).load(character.image).into(ivAvatar)
            ivAvatar.load(character.image) {
                crossfade(true)
//                placeholder(R.drawable.img_placeholder)
//                error(R.drawable.img_errorimage)
            }

            tvName.text = character.name
            tvFirstSeen.text = character.origin.name
            tvLastLocation.text = character.location.name
            tvStatusAndType.text = "${character.status} - ${character.species}"

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