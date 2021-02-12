package com.your.kpopdance.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.your.kpopdance.R
import com.your.kpopdance.databinding.QuickSearchItemBinding
import com.your.kpopdance.viewmodel.SearchViewModel

class QuickSearchAdapter(var artists: List<String> = arrayListOf(), val vm: SearchViewModel) : RecyclerView.Adapter<QuickSearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.quick_search_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.artist = artists[position]
        viewHolder.binding.vm = vm
    }

    override fun getItemCount(): Int = artists.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: QuickSearchItemBinding = QuickSearchItemBinding.bind(itemView)
    }
}