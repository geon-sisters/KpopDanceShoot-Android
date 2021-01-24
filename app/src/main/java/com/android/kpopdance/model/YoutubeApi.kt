package com.android.kpopdance.model

import com.android.kpopdance.data.Youtube
import io.reactivex.Single
import retrofit2.http.GET

interface YoutubeApi {
    @GET("youtube")
    fun get() : Single<List<Youtube>>
}