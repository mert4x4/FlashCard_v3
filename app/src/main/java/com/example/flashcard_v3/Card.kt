package com.example.flashcard_v3

import java.io.Serializable

data class Card(val name: String, val id: Int, val deckDescription: HashMap<Int, Pair<String, String>>) : Serializable {}