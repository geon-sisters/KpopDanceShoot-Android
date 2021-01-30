package com.android.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.YoutubeRepository

class SearchViewModel(private val youtubeRepository: YoutubeRepository) : BaseViewModel() {
    private val TAG = Contract.K_POP_DANCE + SearchViewModel::class.simpleName

    private val _youtubes = MutableLiveData<List<Youtube>>(arrayListOf())
    val youtubes: LiveData<List<Youtube>> get() = _youtubes

    private var query: CharSequence = ""

    init {
        Log.i(TAG, "init")
        getSearchedYoutube()
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

    private fun getSearchedYoutube() {
        addToDisposable(
            youtubeRepository.getAll()
                .subscribe({
                    _youtubes.value = it.filter {item -> item.title.contains(query, ignoreCase = true) }
                    Log.d(TAG, "Success")
                }, {
                    Log.d(TAG, "Fail : $it")
                })
        )
    }
}