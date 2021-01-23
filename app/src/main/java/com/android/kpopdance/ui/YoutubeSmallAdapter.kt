package com.android.kpopdance.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.kpopdance.R
import com.android.kpopdance.data.Youtube
import com.bumptech.glide.Glide

class YoutubeSmallAdapter : RecyclerView.Adapter<YoutubeSmallAdapter.ViewHolder>()  {
    var youtubes = mutableListOf<Youtube>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.youtube_small_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(youtubes[position])
    }

    override fun getItemCount(): Int = youtubes.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        private val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)
        private val smallThumbnailView = itemView.findViewById<ImageView>(R.id.smallThumbnailView)

        fun bind(youtube: Youtube) {
            titleTextView.text = youtube.title
            dateTextView.text = youtube.date
            Glide.with(itemView)
                .load("https://img.youtube.com/vi/" + youtube.id + "/hqdefault.jpg")
                .into(smallThumbnailView)
        }
    }
}