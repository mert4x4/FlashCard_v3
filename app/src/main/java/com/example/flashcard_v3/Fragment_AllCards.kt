package com.example.flashcard_v3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment_AllCards : Fragment(), CardAdapter.OnCardClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__all_cards, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerView_flashCards)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample data (replace this with your actual data)
        val cards: List<Card> = listOf(
            Card("Card1", "ID1", listOf("Key1" to "Value1", "Key2" to "Value2", "Key3" to "Value3")),
            Card("Card2", "ID2", listOf("Key4" to "Value4", "Key5" to "Value5", "Key6" to "Value6")),
            Card("Card3", "ID3", listOf("Key7" to "Value7", "Key8" to "Value8", "Key9" to "Value9"))
        )

        // Initialize CardAdapter with your dataset and pass 'this' as the click listener
        cardAdapter = CardAdapter(cards, this)

        // Set the adapter to the RecyclerView
        recyclerView.adapter = cardAdapter

        return rootView
    }

    // Handle the click event in this method
    override fun onCardClick(card: Card) {


        // Navigate to the detailed card fragment with the selected card as an argument
        view?.findNavController()?.navigate(R.id.action_fragment_AllCards_to_fragment_DetailedCard)
    }
}

