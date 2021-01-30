package com.android.kpopdance.repository

import com.android.kpopdance.data.Youtube
import com.android.kpopdance.model.YoutubeApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YoutubeRepository(private val youtubeApi: YoutubeApi) {
    private var cachedYoutubes: List<Youtube> = arrayListOf()

    var memory: Observable<List<Youtube>> = Observable.create { subscriber ->
        subscriber.onNext(cachedYoutubes)
        subscriber.onComplete()
    }

    var network: Observable<List<Youtube>> = youtubeApi.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext {
            cachedYoutubes = it
        }
}