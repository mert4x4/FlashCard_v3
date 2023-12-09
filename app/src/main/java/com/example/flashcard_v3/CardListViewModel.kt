package com.example.flashcard_v3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcard_v3.Card


class CardListViewModel : ViewModel() {
    private val _cardLiveData = MutableLiveData<Card>()
    val cardLiveData: LiveData<Card> = _cardLiveData

    private val _cardLiveDataList = MutableLiveData<MutableList<Card>>()
    val cardLiveDataList: LiveData<MutableList<Card>> = _cardLiveDataList

    private val _flippedLiveData = MutableLiveData<Boolean>()
    val flippedLiveData: LiveData<Boolean> = _flippedLiveData

    private val _currentIndexLiveData = MutableLiveData<Int>()
    val currentIndexLiveData: LiveData<Int> = _currentIndexLiveData

    private val _currentCardIndexLiveData = MutableLiveData<Int>()
    val currentCardIndexLiveData: LiveData<Int> = _currentCardIndexLiveData


    val cardsList: MutableList<Card> = mutableListOf() // Holds generated cards

    //var currentIndex = 0
    private var flipped = false


    fun loadCards() {
        // Simulate loading cards with dummy data similar to Datasource
        val deckDescriptionMap = HashMap<Int, Pair<String, String>>()
        deckDescriptionMap[0] = "Who painted the Mona Lisa?" to "Leonardo da Vinci"
        deckDescriptionMap[1] = "What is the powerhouse of the cell?" to "Mitochondria"
        deckDescriptionMap[2] = "hello" to "merhaba"

        val card = Card("Card Title", 1, deckDescriptionMap) // Example card
        cardsList.add(card)

        val deckDescriptionMap1 = HashMap<Int, Pair<String, String>>()
        deckDescriptionMap1[0] = "Aaaaaaa" to "bbbbbb"
        deckDescriptionMap1[1] = "ccccc" to "dddddd"
        deckDescriptionMap1[2] = "eeeee" to "ffffff"

        val card1 = Card("Card Title 2", 2, deckDescriptionMap1) // Example card
        cardsList.add(card1)

        _cardLiveData.value = card // Set the initial card
        _cardLiveDataList.value = cardsList
    }
    fun setCurrentIndex(index: Int) {
        _currentIndexLiveData.value = index
    }

    fun setCurrentCardIndex(index: Int) {
        _currentCardIndexLiveData.value = index
    }

    // Update navigation methods to use setCurrentIndex()
    fun navigatePrevious() {
        Log.d("ViewModel", "Navigating to previous card")
        if (_currentCardIndexLiveData.value!! >= 0) {
            val currentIndex = _currentCardIndexLiveData.value ?: 0
            if (currentIndex > 0) {
                setCurrentCardIndex(currentIndex - 1)
            }
        }
        flipCard()
    }

    fun navigateNext() {
        Log.d("ViewModel", "Navigating to next card")
        Log.d("ViewModel", "Current index value: ${_currentCardIndexLiveData.value!!}")
        Log.d("ViewModel", "Card List Size: ${cardsList[_currentIndexLiveData.value!!].deckDescription.size}")
        if (_currentCardIndexLiveData.value!! < cardsList[_currentIndexLiveData.value!!].deckDescription.size) {
            val newIndex = _currentCardIndexLiveData.value!! + 1
            setCurrentCardIndex(newIndex)
            Log.d("ViewModel", "New index: $newIndex")
        }
        flipCard()
    }
//    fun navigatePrevious() {
//        if (currentIndex > 0) {
//            currentIndex--
//            updateCardDetails()
//        }
//    }
//
//    fun navigateNext() {
//        if (currentIndex < cardsList.size - 1) {
//            currentIndex++
//            updateCardDetails()
//        }
//    }

    fun flipCard() {
        flipped = !flipped
        _flippedLiveData.value = flipped
    }

//    private fun updateCardDetails() {
//        val card = cardsList[currentIndex]
//
//        if (currentIndex >= 0 && currentIndex < card.deckDescription.size) {
//            val updatedDescription = if (flipped) {
//                card.deckDescription[currentIndex]?.second to card.deckDescription[currentIndex]?.first
//            } else {
//                card.deckDescription[currentIndex]?.first to card.deckDescription[currentIndex]?.second
//            }
//
//            val updatedDeck = HashMap<Int, Pair<String, String>>()
//            updatedDeck[currentIndex] = updatedDescription as Pair<String, String>
//
//            val updatedCard = Card(card.name, card.id, updatedDeck)
//            _cardLiveData.value = updatedCard
//        }
//    }
//private fun updateCardDetails() {
//    val card = cardsList[currentIndex]
//
//    if (currentIndex >= 0 && currentIndex < card.deckDescription.size) {
//        val updatedDescription = if (flipped) {
//            card.deckDescription[currentIndex]?.second to card.deckDescription[currentIndex]?.first
//        } else {
//            card.deckDescription[currentIndex]?.first to card.deckDescription[currentIndex]?.second
//        }
//
//        card.deckDescription[currentIndex] = updatedDescription as Pair<String, String>
//
//        _cardLiveData.value = card // Notify LiveData about the updated card
//    }
//}
//private fun updateCardDetails() {
//    val card = cardsList.find { it.id.equals(currentIndex.toString())} // Finding the card by ID
//
//    card?.let {
//        val currentDescription = it.deckDescription[currentIndex]
//
//        currentDescription?.let { description ->
//            val updatedDescription = if (flipped) {
//                description.second to description.first
//            } else {
//                description.first to description.second
//            }
//
//            it.deckDescription[currentIndex] = updatedDescription as Pair<String, String>
//            _cardLiveData.value = it // Notify LiveData about the updated card
//        }
//    }
//}
private fun updateCardDetails() {
    if (_currentCardIndexLiveData.value!! >= 0 && _currentCardIndexLiveData.value!! < cardsList.size) {
        val card = cardsList[_currentIndexLiveData.value!!]
        val currentDescription = card.deckDescription[_currentCardIndexLiveData.value!!]

        currentDescription?.let { description ->
            val updatedDescription = if (flipped) {
                description.second to description.first
            } else {
                description.first to description.second
            }

            val updatedCard = card.copy()
            updatedCard.deckDescription[_currentCardIndexLiveData.value!!] = updatedDescription as Pair<String, String>
            _cardLiveData.value = updatedCard // Notify LiveData about the updated card
        }
    }
}

}