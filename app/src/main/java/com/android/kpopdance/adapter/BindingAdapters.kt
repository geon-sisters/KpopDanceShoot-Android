package com.android.kpopdance.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.ui.BookmarkYoutubeAdapter
import com.android.kpopdance.ui.HomeYoutubeAdapter
import com.android.kpopdance.ui.QuickSearchAdapter
import com.android.kpopdance.ui.SearchYoutubeAdapter
import com.android.kpopdance.viewmodel.BookmarkViewModel
import com.android.kpopdance.viewmodel.HomeViewModel
import com.android.kpopdance.viewmodel.SearchViewModel
import com.bumptech.glide.Glide

@BindingAdapter(value = ["homeYoutubes", "homeViewModel"])
fun setHomeYoutubes(view: RecyclerView, items: List<Youtube>, vm: HomeViewModel) {
    view.adapter?.run {
        if (this is HomeYoutubeAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        HomeYoutubeAdapter(items, vm).apply { view.adapter = this }
    }
}

@BindingAdapter(value = ["searchYoutubes", "searchViewModel"])
fun setSearchYoutubes(view: RecyclerView, items: List<Youtube>, vm: SearchViewModel) {
    view.adapter?.run {
        if (this is SearchYoutubeAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        SearchYoutubeAdapter(items, vm).apply {
            view.adapter = this
        }
    }
}

@BindingAdapter(value = ["quickSearches", "quickSearchViewModel"])
fun setQuickSearches(view: RecyclerView, artists: List<String>, vm: SearchViewModel) {
    view.adapter?.run {
        if (this is QuickSearchAdapter) {
            this.artists = artists
            this.notifyDataSetChanged()
        }
    } ?: run {
        QuickSearchAdapter(artists, vm).apply {
            view.adapter = this
        }
    }
}

@BindingAdapter(value = ["bookmarkYoutubes", "bookmarkViewModel"])
fun setBookmarkYoutubes(view: RecyclerView, items: List<Youtube>, vm: BookmarkViewModel) {
    view.adapter?.run {
        if (this is BookmarkYoutubeAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        BookmarkYoutubeAdapter(items, vm).apply {
            view.adapter = this
        }
    }
}

@BindingAdapter("youtubeThumbnail")
fun setYoutubeThumbnail(imageView: ImageView, youtubeId: String) {
    Glide.with(imageView)
        .load("https://img.youtube.com/vi/$youtubeId/maxresdefault.jpg")
        .into(imageView)
}