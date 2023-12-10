package com.example.flashcard_v3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcard_v3.Card


class CardListViewModel : ViewModel() {

    private val _currentDeckData = MutableLiveData<MutableList<Deck>>()
    val currentDeckData: LiveData<MutableList<Deck>> = _currentDeckData

    private val _currentCardData = MutableLiveData<MutableList<Card>>()
    val currentCardData: LiveData<MutableList<Card>> = _currentCardData

    private val _currentCardIndexLiveData = MutableLiveData<Int>()
    val currentCardIndex: LiveData<Int> = _currentCardIndexLiveData

    private val _currentDeckIndexLiveData = MutableLiveData<Int>()
    val currentDeckIndex: LiveData<Int> = _currentDeckIndexLiveData

    private var deckList = mutableListOf<Deck>()

    private val _currentFlippedData = MutableLiveData<Boolean>()
    val currentFliped = _currentFlippedData

    fun loadCards() {
        deckList = mutableListOf()
        setCurrentCardIndex(0)
        setCurrentDeckIndex(0)
        setCurrentFliped(false)

        val card1 = Card(id = 0, question = "What is the capital of Japan?", answer = "Tokyo")
        val card2 = Card(id = 1, question = "What is the largest mammal on Earth?", answer = "Blue Whale")
        val card3 = Card(id = 2, question = "Who painted the Mona Lisa?", answer = "Leonardo da Vinci")

        // Create a deck with multiple cards
        val deck1 = Deck(id = 0, name = "Trivia Deck", cards = listOf(card1, card2, card3))

        val card4 = Card(id = 0, question = "What is the chemical symbol for water?", answer = "H2O")
        val card5 = Card(id = 1, question = "Who developed the theory of relativity?", answer = "Albert Einstein")
        val card6 = Card(id = 2, question = "What is the powerhouse of the cell?", answer = "Mitochondria")

        // Create a science deck with multiple cards
        val deck2 = Deck(id = 1, name = "Science Deck", cards = listOf(card4, card5, card6))

        deckList.add(deck1)
        deckList.add(deck2)

        _currentDeckData.value = deckList

    }
    fun setCurrentDeckIndex(index: Int) {
        _currentDeckIndexLiveData.value = index
    }

    fun setCurrentCardIndex(index: Int) {
        _currentCardIndexLiveData.value = index
    }

    fun setCurrentFliped(flipped: Boolean){
        _currentFlippedData.value = flipped
    }

    fun navigatePrevious() {
        var prevData = currentCardIndex.value
        if (prevData!! > 0) {
            setCurrentCardIndex(prevData - 1)
        }
        setCurrentFliped(false)
    }

    fun navigateNext() {
        var prevData = currentCardIndex.value
        if (prevData!! < deckList.size - 1) {
            setCurrentCardIndex(prevData + 1)
        }
        setCurrentFliped(false)
    }

}