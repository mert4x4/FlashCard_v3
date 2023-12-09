package com.example.flashcard_v3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class CardAdapter(
    private var dataset: List<Card>,
    private val clickListener: OnCardClickListener
) : RecyclerView.Adapter<CardAdapter.ItemViewHolder>() {

    interface OnCardClickListener {
        fun onCardClick(card: Card)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.textView1)
        private val profileDescView: TextView = itemView.findViewById(R.id.textView2)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val card = dataset[position]
                    clickListener.onCardClick(card)
                }
            }
        }

        fun bind(card: Card) {
            nameView.text = card.name
            profileDescView.text = card.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.all_cards_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val card = dataset[position]
        holder.bind(card)
    }

    fun updateData(cards: Card) {
        dataset = listOf(cards)
        notifyDataSetChanged()
    }
}
