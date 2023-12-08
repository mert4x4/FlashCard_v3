package com.example.flashcard_v3

import java.io.Serializable

data class Card(val name: String, val id: String, val deckDescription: List<Pair<String, String>>) : Serializable {}