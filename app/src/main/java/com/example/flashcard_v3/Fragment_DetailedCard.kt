package com.example.flashcard_v3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

const val ARG_CARD = "arg_card"

class Fragment_DetailedCard : Fragment() {

    private var card: Card? = null
    private lateinit var textViewId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_detailed_cards, container, false)
        textViewId = rootView.findViewById(R.id.textView)


        return rootView
    }


}
