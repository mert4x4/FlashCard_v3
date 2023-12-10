package com.example.flashcard_v3

import java.io.Serializable

data class Card(
    val id: Int,
    val question: String,
    val answer: String
): Serializable {}