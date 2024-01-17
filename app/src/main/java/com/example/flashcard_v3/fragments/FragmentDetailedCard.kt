package com.example.flashcard_v3.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.flashcard_v3.viewModels.CardListViewModel
import com.example.flashcard_v3.R

//const val ARG_CARD = "arg_card"

class FragmentDetailedCard : Fragment() {
    private lateinit var descriptionTextView: TextView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var viewModel: CardListViewModel
    private lateinit var nameTextView: TextView
    private lateinit var indexTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailed_card, container, false)
        prevButton = view.findViewById(R.id.prevButton)
        nextButton = view.findViewById(R.id.nextButton)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        nameTextView = view.findViewById(R.id.nameTextView)
        indexTextView = view.findViewById(R.id.textView_index)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CardListViewModel::class.java)

        viewModel.currentDeckIndex.observe(viewLifecycleOwner) { index ->
            nameTextView.text = viewModel.currentDeckData.value?.get(viewModel.currentDeckIndex.value ?: -1)?.name ?: "deck name error"
        //            Log.v("current deck index",viewModel.currentDeckIndex.value.toString())
        }

        viewModel.currentCardIndex.observe(viewLifecycleOwner) { index ->
            val currentDeckIndex = viewModel.currentDeckIndex.value ?: -1
            val currentCardIndex = viewModel.currentCardIndex.value ?: -1

            if (currentDeckIndex >= 0 && currentCardIndex >= 0) {
                val deckList = viewModel.currentDeckData.value

                if (deckList != null && currentDeckIndex < deckList.size) {
                    val currentDeck = deckList[currentDeckIndex]
                    indexTextView.text = (currentCardIndex+1).toString() + "/"+ currentDeck.cards.size.toString()
                    val cardList = currentDeck.cards

                    if (currentCardIndex < cardList.size) {
                        val currentCard = cardList[currentCardIndex]
                        descriptionTextView.text = currentCard.question
                    }
                }
            }
            viewModel.setCurrentFliped(false)
            Log.v("current card index",viewModel.currentCardIndex.value.toString())

        }

        prevButton.setOnClickListener {
            viewModel.navigatePrevious()
        }

        nextButton.setOnClickListener {
            viewModel.navigateNext()
        }

        descriptionTextView.setOnClickListener{
            if(viewModel.currentFliped.value == false){
                val currentDeckIndex = viewModel.currentDeckIndex.value ?: -1
                val currentCardIndex = viewModel.currentCardIndex.value ?: -1

                if (currentDeckIndex >= 0 && currentCardIndex >= 0) {
                    val deckList = viewModel.currentDeckData.value

                    if (deckList != null && currentDeckIndex < deckList.size) {
                        val currentDeck = deckList[currentDeckIndex]

                        val cardList = currentDeck.cards

                        if (currentCardIndex < cardList.size) {
                            val currentCard = cardList[currentCardIndex]
                            descriptionTextView.text = currentCard.answer
                        }
                    }
                }

                Log.v("current card index",viewModel.currentCardIndex.value.toString())
                viewModel.setCurrentFliped(true)
            }
            else{
                val currentDeckIndex = viewModel.currentDeckIndex.value ?: -1
                val currentCardIndex = viewModel.currentCardIndex.value ?: -1

                if (currentDeckIndex >= 0 && currentCardIndex >= 0) {
                    val deckList = viewModel.currentDeckData.value

                    if (deckList != null && currentDeckIndex < deckList.size) {
                        val currentDeck = deckList[currentDeckIndex]

                        val cardList = currentDeck.cards

                        if (currentCardIndex < cardList.size) {
                            val currentCard = cardList[currentCardIndex]
                            descriptionTextView.text = currentCard.question
                        }
                    }
                }

                Log.v("current card index",viewModel.currentCardIndex.value.toString())
                viewModel.setCurrentFliped(false)
            }
        }

    }
}







