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
        deckDescriptionMap[3] = "What is the capital of France?" to "Paris"
        deckDescriptionMap[4] = "Who wrote 'Romeo and Juliet'?" to "William Shakespeare"
        deckDescriptionMap[5] = "What is the largest mammal on Earth?" to "Blue Whale"
        deckDescriptionMap[6] = "In which year did the Titanic sink?" to "1912"
        deckDescriptionMap[7] = "What is the currency of Japan?" to "Japanese Yen"
        deckDescriptionMap[8] = "Who discovered penicillin?" to "Alexander Fleming"
        deckDescriptionMap[9] = "What is the boiling point of water?" to "100 degrees Celsius"

        val card = Card("General Knowledge", 1, deckDescriptionMap) // Example card
        cardsList.add(card)

        val deckDescriptionMap1 = HashMap<Int, Pair<String, String>>()
        deckDescriptionMap[0] = "What is the capital of Germany?" to "Berlin"
        deckDescriptionMap[1] = "Who invented the telephone?" to "Alexander Graham Bell"
        deckDescriptionMap[2] = "What is the tallest mountain in the world?" to "Mount Everest"
        deckDescriptionMap[3] = "Who painted 'Starry Night'?" to "Vincent van Gogh"
        deckDescriptionMap[4] = "What is the largest planet in our solar system?" to "Jupiter"
        deckDescriptionMap[5] = "In which year did World War II end?" to "1945"
        deckDescriptionMap[6] = "Who is known as the 'Father of Computer Science'?" to "Alan Turing"
        deckDescriptionMap[7] = "What is the currency of Brazil?" to "Brazilian Real"
        deckDescriptionMap[8] = "Which element has the chemical symbol 'H'?" to "Hydrogen"
        deckDescriptionMap[9] = "Who wrote 'To Kill a Mockingbird'?" to "Harper Lee"

        val card1 = Card("Trivia", 2, deckDescriptionMap) // Example card
        cardsList.add(card1)

        val deckDescriptionMap2 = HashMap<Int, Pair<String, String>>()
        deckDescriptionMap2[0] = "What is the chemical symbol for water?" to "H2O"
        deckDescriptionMap2[1] = "Who developed the theory of relativity?" to "Albert Einstein"
        deckDescriptionMap2[2] = "What is the powerhouse of the cell?" to "Mitochondria"
        deckDescriptionMap2[3] = "Which planet is known as the Red Planet?" to "Mars"
        deckDescriptionMap2[4] = "What is the largest organ in the human body?" to "Skin"
        deckDescriptionMap2[5] = "What is the process by which plants make their own food?" to "Photosynthesis"
        deckDescriptionMap2[6] = "Who discovered penicillin?" to "Alexander Fleming"
        deckDescriptionMap2[7] = "What is the smallest prime number?" to "2"
        deckDescriptionMap2[8] = "Which gas do plants absorb during photosynthesis?" to "Carbon dioxide"
        deckDescriptionMap2[9] = "What is the chemical symbol for gold?" to "Au"

        val card2 = Card("Science Trivia", 3, deckDescriptionMap2)
        cardsList.add(card2)



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