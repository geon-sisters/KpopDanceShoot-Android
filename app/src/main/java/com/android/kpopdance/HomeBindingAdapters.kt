package com.android.kpopdance

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.ui.YoutubeBigAdapter
import com.android.kpopdance.viewmodel.HomeViewModel
import com.bumptech.glide.Glide

@BindingAdapter(value = ["bigYoutubes", "viewModel"])
fun setBigYoutubes(view: RecyclerView, items: List<Youtube>, vm: HomeViewModel) {
    view.adapter?.run {
        if (this is YoutubeBigAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        YoutubeBigAdapter(items, vm).apply { view.adapter = this }
    }
}

@BindingAdapter("bigYoutubeThumbnail")
fun setBigYoutubeThumbnail(imageView: ImageView, youtubeId: String) {
    Glide.with(imageView)
        .load("https://img.youtube.com/vi/$youtubeId/maxresdefault.jpg")
        .into(imageView)
}