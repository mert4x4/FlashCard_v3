package com.example.flashcard_v3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

//const val ARG_CARD = "arg_card"

class Fragment_DetailedCard : Fragment() {
    private lateinit var descriptionTextView: TextView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var flipButton: Button
    private lateinit var viewModel: CardListViewModel

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailed_cards, container, false)
        prevButton = view.findViewById(R.id.prevButton)
        nextButton = view.findViewById(R.id.nextButton)
        flipButton = view.findViewById(R.id.flipButton)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)

        return view;
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(CardListViewModel::class.java)
        viewModel = ViewModelProvider(requireActivity()).get(CardListViewModel::class.java)
        viewModel.loadCards()

        Log.d("Fragment", "Current viewmodeldata is: ${viewModel.currentIndexLiveData.value!!}")
        viewModel.setCurrentIndex(viewModel.currentIndexLiveData.value!!)
        viewModel.setCurrentCardIndex(0)



        viewModel.cardLiveData.observe(viewLifecycleOwner) { card ->
            Log.d("Fragment", "Current id is: ${card.id}")

            val currentDescription = card.deckDescription[viewModel.currentIndexLiveData.value]
            descriptionTextView.text = currentDescription?.first ?: ""
        }

        viewModel.flippedLiveData.observe(viewLifecycleOwner) { flipped ->
            viewModel.currentIndexLiveData.observe(viewLifecycleOwner) { index ->
                Log.d("Fragment", "Current index changed to: $index")
            }
            val card = viewModel.cardsList[viewModel.currentIndexLiveData.value!!]

            if (viewModel.currentCardIndexLiveData.value!! >= 0 && viewModel.currentCardIndexLiveData.value!! < card.deckDescription.size) {
                val description = if (flipped) {
                    card.deckDescription[viewModel.currentCardIndexLiveData.value!!]?.second
                } else {
                    card.deckDescription[viewModel.currentCardIndexLiveData.value!!]?.first
                }

                descriptionTextView.text = description
            }
        }

        prevButton.setOnClickListener {
            viewModel.navigatePrevious()
        }

        nextButton.setOnClickListener {
            viewModel.navigateNext()
        }

        flipButton.setOnClickListener {
            viewModel.flipCard()
        }
    }



    }



