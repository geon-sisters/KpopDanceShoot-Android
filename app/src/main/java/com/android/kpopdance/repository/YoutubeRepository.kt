package com.android.kpopdance.repository

import android.util.Log
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.model.YoutubeApi
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YoutubeRepository(private val youtubeApi: YoutubeApi) {
    private val TAG = Contract.K_POP_DANCE + YoutubeRepository::class.simpleName

    private var cachedYoutubes: List<Youtube> = arrayListOf()

    private var memory: Observable<List<Youtube>> = Observable.create { subscriber ->
        subscriber.onNext(cachedYoutubes)
        subscriber.onComplete()
    }

    private var network: Observable<List<Youtube>> = youtubeApi.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext {
            cachedYoutubes = it
        }

    fun getAll(): Maybe<List<Youtube>> {
        return Observable.concat(memory, network)
            .filter {
                val isNotEmpty = it.isNotEmpty()
                Log.d(TAG, "cached: $isNotEmpty")
                isNotEmpty
            }
            .firstElement()
    }
}