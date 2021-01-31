package com.android.kpopdance.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.android.kpopdance.R

class QuickSearchAdapter : RecyclerView.Adapter<QuickSearchAdapter.ViewHolder>() {
    var singers = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.quick_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(singers[position])
    }

    override fun getItemCount(): Int = singers.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val buttonText = itemView.findViewById<Button>(R.id.singerButton)

        fun bind(singer: String) {
            buttonText.text = singer
        }
    }
}