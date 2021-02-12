package com.android.kpopdance.model

import com.android.kpopdance.data.Youtube
import io.reactivex.Observable
import retrofit2.http.GET

interface YoutubeApi {
    @GET("youtube")
    fun get() : Observable<List<Youtube>>
}