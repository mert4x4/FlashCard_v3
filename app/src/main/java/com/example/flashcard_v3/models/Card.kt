package com.example.flashcard_v3.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Card(
    val id: Int,
    val question: String,
    val answer: String
): Serializable {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem == newItem
            }
        }
    }
}