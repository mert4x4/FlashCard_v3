package com.example.flashcard_v3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcard_v3.models.Deck
import com.example.flashcard_v3.databinding.AllCardsItemBinding


class CardAdapter(private val clickListener: OnDeckClickListener) :
    ListAdapter<Deck, CardAdapter.ItemViewHolder>(Deck.DIFF_CALLBACK) {


    interface OnDeckClickListener {
        fun onDeckClick(deck: Deck)
    }

    inner class ItemViewHolder(private val binding: AllCardsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val deck = getItem(position)
                    clickListener.onDeckClick(deck)
                }
            }
        }

        fun bind(deck: Deck?) {
            deck?.let {
                binding.deck = it
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AllCardsItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val deck = getItem(position)
        holder.bind(deck)
    }
}
