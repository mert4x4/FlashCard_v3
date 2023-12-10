package com.example.flashcard_v3

import java.io.Serializable


data class Deck(
    val id: Int,
    val name: String,
    val cards: List<Card>
) : Serializable{}