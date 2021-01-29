package com.android.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.YoutubeRepository

class HomeViewModel(private val youtubeRepository: YoutubeRepository) : BaseViewModel() {
    companion object {
        private val TAG = "[KPopDance]" + HomeViewModel::class.simpleName
    }

    val youtubes: MutableLiveData<List<Youtube>> = MutableLiveData(arrayListOf())

    private val _clickedYoutubeId = MutableLiveData<Event<String>>()
    val clickedYoutubeId: LiveData<Event<String>> get() = _clickedYoutubeId

    init {
        Log.i(TAG, "init")

        addToDisposable(youtubeRepository.disposal
            .subscribe({
                youtubes.value = it
                Log.d(TAG, "성공 : " + youtubes.value)
            }, {
                Log.d(TAG, "실패 : $it")
            }))
    }

    fun onBigYoutubeClicked(youtubeId: String) {
        _clickedYoutubeId.value = Event(youtubeId)
    }
}