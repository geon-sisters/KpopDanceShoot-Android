package com.android.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.kpopdance.contract.Contract.Companion.K_POP_DANCE
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.BookmarkRepository
import com.android.kpopdance.repository.YoutubeRepository

class HomeViewModel(private val youtubeRepository: YoutubeRepository, bookmarkRepository: BookmarkRepository) : BaseViewModel(bookmarkRepository) {
    override fun onPostBookmarkClicked() = getAllYoutube()

    private val TAG = K_POP_DANCE + HomeViewModel::class.simpleName

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