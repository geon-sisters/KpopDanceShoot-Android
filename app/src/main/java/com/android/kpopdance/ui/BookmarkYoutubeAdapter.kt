package com.android.kpopdance.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.kpopdance.R
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.databinding.BookmarkYoutubeItemBinding
import com.android.kpopdance.viewmodel.BookmarkViewModel

class BookmarkYoutubeAdapter(var items: List<Youtube> = arrayListOf(), val vm: BookmarkViewModel) : RecyclerView.Adapter<BookmarkYoutubeAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bookmark_youtube_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.item = items[position]
        viewHolder.binding.vm = vm
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding: BookmarkYoutubeItemBinding = BookmarkYoutubeItemBinding.bind(itemView)
    }
}