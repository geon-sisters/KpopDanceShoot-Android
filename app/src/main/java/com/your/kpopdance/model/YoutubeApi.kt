package com.your.kpopdance.model

import com.your.kpopdance.data.Youtube
import io.reactivex.Observable
import retrofit2.http.GET

interface YoutubeApi {
    @GET("youtube")
    fun get() : Observable<List<Youtube>>
}