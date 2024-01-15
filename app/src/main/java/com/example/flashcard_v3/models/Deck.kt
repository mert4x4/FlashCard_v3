package com.example.flashcard_v3.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable


data class Deck(
    val id: Int,
    val name: String,
    var cards: List<Card>
) : Serializable{
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Deck>() {
            override fun areItemsTheSame(oldItem: Deck, newItem: Deck): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Deck, newItem: Deck): Boolean {
                return oldItem == newItem
            }
        }
    }

}