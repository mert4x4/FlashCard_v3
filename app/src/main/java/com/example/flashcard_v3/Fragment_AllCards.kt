package com.example.flashcard_v3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment_AllCards : Fragment(), CardAdapter.OnDeckClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter
    private lateinit var sharedViewModel: CardListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__all_cards, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerView_flashCards)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        sharedViewModel = ViewModelProvider(requireActivity()).get(CardListViewModel::class.java)

        cardAdapter = CardAdapter(emptyList(), this)
        recyclerView.adapter = cardAdapter

        sharedViewModel.currentDeckData.observe(viewLifecycleOwner, { deck ->
            for(i in deck) cardAdapter.updateData(i)

        })

        sharedViewModel.loadCards()


        return rootView
    }


    // Handle the click event in this method
    override fun onDeckClick(deck: Deck) {
        Log.d("Fragment", "Clicked deck ID: ${deck.id}")
        sharedViewModel.setCurrentDeckIndex(deck.id)
        Log.d("Fragment", "Set current deck index to: ${deck.id}")
        view?.findNavController()?.navigate(R.id.action_fragment_AllCards_to_fragment_DetailedCard)
    }
}


