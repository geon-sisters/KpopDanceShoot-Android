package com.android.kpopdance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.kpopdance.contract.Contract.Companion.K_POP_DANCE
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.repository.YoutubeRepository
import io.reactivex.Observable

class HomeViewModel(private val youtubeRepository: YoutubeRepository) : BaseViewModel() {
    private val TAG = K_POP_DANCE + HomeViewModel::class.simpleName

    private val _youtubes = MutableLiveData<List<Youtube>>(arrayListOf())
    val youtubes: LiveData<List<Youtube>> get() = _youtubes

    private val _clickedYoutubeId = MutableLiveData<Event<String>>()
    val clickedYoutubeId: LiveData<Event<String>> get() = _clickedYoutubeId

    init {
        Log.i(TAG, "init")
        addToDisposable(
            Observable.concat(
                youtubeRepository.memory,
                youtubeRepository.network)
                .filter {
                    val isNotEmpty = it.isNotEmpty()
                    Log.d(TAG, "cached: $isNotEmpty")
                    isNotEmpty
                }
                .firstElement()
                .subscribe({
                    _youtubes.value = it
                    Log.d(TAG, "Success")
                }, {
                    Log.d(TAG, "Fail : $it")
                })
        )
    }

    fun onYoutubeClicked(youtubeId: String) {
        _clickedYoutubeId.value = Event(youtubeId)
    }
}