package com.android.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.BookmarkRepository
import com.android.kpopdance.repository.YoutubeRepository

class BookmarkViewModel(private val youtubeRepository: YoutubeRepository, bookmarkRepository: BookmarkRepository) : BaseViewModel(bookmarkRepository) {
    override fun onPostBookmarkClicked() = getBookmarkedYoutube()

    private val TAG = Contract.YOUR_KDANCE + BookmarkViewModel::class.simpleName

    private val _youtubes = MutableLiveData<List<Youtube>>(arrayListOf())
    val youtubes: LiveData<List<Youtube>> get() = _youtubes

    init {
        Log.i(TAG, "init")
        getBookmarkedYoutube()
    }

    private fun getBookmarkedYoutube() {
        addToDisposable(
            youtubeRepository.getBookmarked()
                .subscribe({
                    _youtubes.value = it
                    Log.d(TAG, "Success")
                }, {
                    Log.d(TAG, "Fail : $it")
                })
        )
    }
}
