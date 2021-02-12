package com.your.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.your.kpopdance.contract.Contract
import com.your.kpopdance.data.Youtube
import com.your.kpopdance.repository.BookmarkRepository
import com.your.kpopdance.repository.YoutubeRepository

class SearchViewModel(private val youtubeRepository: YoutubeRepository, bookmarkRepository: BookmarkRepository) : BaseViewModel(bookmarkRepository) {
    override fun onPostBookmarkClicked() = getSearchedYoutube()

    private val TAG = Contract.YOUR_KDANCE + SearchViewModel::class.simpleName

    private val _youtubes = MutableLiveData<List<Youtube>>(arrayListOf())
    val youtubes: LiveData<List<Youtube>> get() = _youtubes

    private val _quickSearches = MutableLiveData<List<String>>(arrayListOf())
    val quickSearches: LiveData<List<String>> get() = _quickSearches

    private var _clickedQuickSearch = MutableLiveData<Event<String>>()
    val clickedQuickSearch: LiveData<Event<String>> get() = _clickedQuickSearch

    private var query: CharSequence = ""

    init {
        Log.i(TAG, "init")
        getSearchedYoutube()
        getQuickSearches()
    }

    fun onEditTextChanged(query: CharSequence) {
        this.query = query
        if (query.isEmpty()) {
            getSearchedYoutube()
        }
    }

    fun onSearchButtonClicked() {
        Log.i(TAG, "Search button clicked: $query")
        getSearchedYoutube()
    }

    fun onQuickSearchButtonClicked(artist: String) {
        Log.i(TAG, "QuickSearch button clicked $artist")
        _clickedQuickSearch.value = Event(artist)
    }

    private fun getSearchedYoutube() {
        addToDisposable(
            youtubeRepository.getSearched(query)
                .subscribe({
                    _youtubes.value = it
                    Log.d(TAG, "GetSearchedYoutube Success")
                }, {
                    Log.d(TAG, "GetSearchedYoutube Fail : $it")
                })
        )
    }

    private fun getQuickSearches() {
        addToDisposable(
            youtubeRepository.getQuickSearch()
                .subscribe({
                    _quickSearches.value = it.distinct().sorted()
                    Log.d(TAG, "GetQuickSearches Success")
                }, {
                    Log.d(TAG, "GetQuickSearches Fail : $it")
                })
        )
    }
}