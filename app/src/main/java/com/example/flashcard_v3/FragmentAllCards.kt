package com.example.flashcard_v3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcard_v3.models.Deck
import com.example.flashcard_v3.databinding.FragmentAllCardsBinding

class FragmentAllCards : Fragment(), CardAdapter.OnDeckClickListener {



    private lateinit var binding: FragmentAllCardsBinding
    private val cardAdapter = CardAdapter(this)

    companion object {
        @JvmStatic @BindingAdapter("cardAdapter")
        fun setCardAdapter(recyclerView: RecyclerView, adapter: CardAdapter?) {
            adapter?.let {
                recyclerView.adapter = it
            }
        }
    }
    private lateinit var sharedViewModel: CardListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_all_cards, container, false
        )

        binding.recyclerViewFlashCards.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardAdapter
        }

        sharedViewModel = ViewModelProvider(requireActivity()).get(CardListViewModel::class.java)

        sharedViewModel.currentDeckData.observe(viewLifecycleOwner, { decks ->
            cardAdapter.submitList(decks)
        })

        sharedViewModel.loadCards()

        return binding.root
    }

    // Handle the click event in this method
    override fun onDeckClick(deck: Deck) {
        Log.d("Fragment", "Clicked deck ID: ${deck.id}")
        sharedViewModel.setCurrentDeckIndex(deck.id)
        Log.d("Fragment", "Set current deck index to: ${deck.id}")
        view?.findNavController()?.navigate(R.id.action_fragment_AllCards_to_fragment_DetailedCard)
    }
}


