package com.android.kpopdance.repository

import com.android.kpopdance.model.YoutubeApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YoutubeRepository(private val youtubeApi: YoutubeApi) {
    var disposal = youtubeApi.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}