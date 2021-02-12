package com.your.kpopdance.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.your.kpopdance.R
import com.your.kpopdance.data.Youtube
import com.your.kpopdance.databinding.SearchYoutubeItemBinding
import com.your.kpopdance.viewmodel.SearchViewModel

class SearchYoutubeAdapter(var items: List<Youtube> = arrayListOf(), val vm: SearchViewModel)  : RecyclerView.Adapter<SearchYoutubeAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_youtube_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.item = items[position]
        viewHolder.binding.vm = vm
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding: SearchYoutubeItemBinding = SearchYoutubeItemBinding.bind(itemView)
    }
}