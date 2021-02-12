package com.your.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.your.kpopdance.contract.Contract.Companion.YOUR_KDANCE
import com.your.kpopdance.data.Youtube
import com.your.kpopdance.repository.BookmarkRepository
import com.your.kpopdance.repository.YoutubeRepository

class HomeViewModel(private val youtubeRepository: YoutubeRepository, bookmarkRepository: BookmarkRepository) : BaseViewModel(bookmarkRepository) {
    override fun onPostBookmarkClicked() = getAllYoutube()

    private val TAG = YOUR_KDANCE + HomeViewModel::class.simpleName
    private val _youtubes = MutableLiveData<List<Youtube>>(arrayListOf())
    val youtubes: LiveData<List<Youtube>> get() = _youtubes

    init {
        Log.i(TAG, "init")
        getAllYoutube()
    }

    private fun getAllYoutube() {
        addToDisposable(
            youtubeRepository.getAll()
                .subscribe({
                    _youtubes.value = it
                    Log.d(TAG, "Success")
                }, {
                    Log.d(TAG, "Fail : $it")
                })
        )
    }
}