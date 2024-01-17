package com.example.flashcard_v3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcard_v3.adapters.CardAdapter
import com.example.flashcard_v3.models.Card
import com.example.flashcard_v3.models.Deck
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


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

        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("decks")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (deckSnapshot in dataSnapshot.children) {
                    val deckId = deckSnapshot.key?.toInt() ?: 0
                    val deckName = deckSnapshot.child("name").getValue(String::class.java) ?: "Default Deck"
                    val cards = mutableListOf<Card>()

                    for (cardSnapshot in deckSnapshot.child("cards").children) {
                        val cardId = cardSnapshot.child("id").getValue(Int::class.java) ?: 0
                        val question = cardSnapshot.child("question").getValue(String::class.java) ?: ""
                        val answer = cardSnapshot.child("answer").getValue(String::class.java) ?: ""

                        val card = Card(cardId, question, answer)
                        cards.add(card)
                    }

                    val deck = Deck(deckId, deckName, cards)
                    deckList.add(deck)
                }

                _currentDeckData.value = deckList
                _cardAdapter.value = CardAdapter(this@CardListViewModel)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("ViewModel", "Error getting decks from Firebase", databaseError.toException())
            }
        })
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
        Log.d("ViewModel", "Clicked on deck: ${deck.name}")
    }


}