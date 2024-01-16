package com.example.flashcard_v3

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcard_v3.models.Card
import com.example.flashcard_v3.models.Deck
import org.xmlpull.v1.XmlPullParser


class CardListViewModel : ViewModel(), CardAdapter.OnDeckClickListener {

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


    private val _cardAdapter = MutableLiveData<CardAdapter>()
    val cardAdapter: LiveData<CardAdapter> = _cardAdapter

    init {
        _cardAdapter.value = CardAdapter(this)
    }

    fun loadCards() {
        deckList = mutableListOf()
        setCurrentCardIndex(0)
        setCurrentDeckIndex(0)
        setCurrentFliped(false)

        val card1 = Card(id = 0, question = "What is the capital of Japan?", answer = "Tokyo")
        val card2 = Card(id = 1, question = "What is the largest mammal on Earth?", answer = "Blue Whale")
        val card3 = Card(id = 2, question = "Who painted the Mona Lisa?", answer = "Leonardo da Vinci")

        val deck1 = Deck(id = 0, name = "Trivia Deck", cards = listOf(card1, card2, card3))

        val card4 = Card(id = 0, question = "What is the chemical symbol for water?", answer = "H2O")
        val card5 = Card(id = 1, question = "Who developed the theory of relativity?", answer = "Albert Einstein")
        val card6 = Card(id = 2, question = "What is the powerhouse of the cell?", answer = "Mitochondria")

        val deck2 = Deck(id = 1, name = "Science Deck", cards = listOf(card4, card5, card6))

        val card7 = Card(id = 0, question = "Red", answer = "Rot")
        val card8 = Card(id = 1, question = "Blue", answer = "Blau")
        val card9 = Card(id = 2, question = "Green", answer = "Grün")
        val card10 = Card(id = 3, question = "Yellow", answer = "Gelb")
        val card11 = Card(id = 4, question = "Purple", answer = "Lila")
        val card12 = Card(id = 5, question = "Orange", answer = "Orange")

        val deck3 = Deck(id = 2, name = "Colors Deck", cards = listOf(card7, card8, card9, card10, card11, card12))

        deckList.add(deck1)
        deckList.add(deck2)
        deckList.add(deck3)

        _currentDeckData.value = deckList

        _cardAdapter.value = CardAdapter(this)

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
        val currentDeckIndex = currentDeckIndex.value ?: -1
        val currentCardIndex = currentCardIndex.value ?: -1

        if (currentDeckIndex >= 0 && currentCardIndex > 0) {
            setCurrentCardIndex(currentCardIndex - 1)
            setCurrentFliped(false)
        }

    }

    fun navigateNext() {
        val currentDeckIndex = currentDeckIndex.value ?: -1
        val currentCardIndex = currentCardIndex.value ?: -1

        if (currentDeckIndex >= 0 && currentCardIndex < deckList[currentDeckIndex].cards.size - 1) {
            setCurrentCardIndex(currentCardIndex + 1)
            setCurrentFliped(false)
        }
    }

    override fun onDeckClick(deck: Deck) {
        // Handle the click event as needed
        Log.d("ViewModel", "Clicked on deck: ${deck.name}")
    }


}