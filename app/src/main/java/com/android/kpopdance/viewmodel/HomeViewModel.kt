package com.android.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.YoutubeRepository

class HomeViewModel(private val youtubeRepository: YoutubeRepository) : BaseViewModel() {
    companion object {
        private val TAG = "[KPopDance]" + HomeViewModel::class.simpleName
    }

    val youtubes: MutableLiveData<List<Youtube>> = MutableLiveData(arrayListOf())

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
}