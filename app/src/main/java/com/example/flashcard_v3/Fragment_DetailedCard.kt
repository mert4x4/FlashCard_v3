package com.example.flashcard_v3

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

//const val ARG_CARD = "arg_card"

class Fragment_DetailedCard : Fragment() {
    private lateinit var descriptionTextView: TextView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var flipButton: Button
    private lateinit var viewModel: CardListViewModel
    private lateinit var nameTextView: TextView
    private lateinit var indexTextView: TextView

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__detailed_card, container, false)
        prevButton = view.findViewById(R.id.prevButton)
        nextButton = view.findViewById(R.id.nextButton)
        flipButton = view.findViewById(R.id.flipButton)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        nameTextView = view.findViewById(R.id.nameTextView)
        indexTextView = view.findViewById(R.id.textView_index)

        return view;
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CardListViewModel::class.java)

        // Ensure that you have a valid index before setting the current index and card data

        viewModel.currentDeckIndex.observe(viewLifecycleOwner) { index ->
            nameTextView.text = viewModel.currentDeckData.value?.get(viewModel.currentDeckIndex.value ?: -1)?.name ?: "deck name error"
            val color = Color.rgb(255, 192, 217)
            descriptionTextView.setBackgroundColor(color);
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
            val color = Color.rgb(255, 192, 217)
            descriptionTextView.setBackgroundColor(color);
        }

        nextButton.setOnClickListener {
            viewModel.navigateNext()
            val color = Color.rgb(255, 192, 217)
            descriptionTextView.setBackgroundColor(color);
        }

        flipButton.setOnClickListener{
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

                val color = Color.rgb(138, 205, 215)
                descriptionTextView.setBackgroundColor(color);
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

                val color = Color.rgb(255, 192, 217)
                descriptionTextView.setBackgroundColor(color);
            }
        }
    }
}







