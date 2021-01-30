package com.android.kpopdance.repository

import android.util.Log
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.data.Youtube
import com.android.kpopdance.model.BookmarkDao
import com.android.kpopdance.model.YoutubeApi
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YoutubeRepository(private val youtubeApi: YoutubeApi, private val bookmarkDao: BookmarkDao) {
    private val TAG = Contract.K_POP_DANCE + YoutubeRepository::class.simpleName

    private var cachedYoutubes: List<Youtube> = arrayListOf()

    private var memory: Observable<List<Youtube>> = Observable.create { subscriber ->
        subscriber.onNext(cachedYoutubes)
        subscriber.onComplete()
    }

    private var remote: Observable<List<Youtube>> = youtubeApi.get()
        .subscribeOn(Schedulers.io())
        .map {
            val bookmarkId = bookmarkDao.getAll().map{ bookmark -> bookmark.youtubeId }
            it.map { youtube ->
                if (bookmarkId.contains(youtube.id)) {
                    youtube.isBookmarked = true
                }
            }
            it
        }
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext {
            cachedYoutubes = it
        }

    fun getAll(): Maybe<List<Youtube>> {
        return Observable.concat(memory, remote)
            .filter {
                val isNotEmpty = it.isNotEmpty()
                Log.d(TAG, "cached: $isNotEmpty")
                isNotEmpty
            }
            .firstElement()
    }

    fun getSearched(query: CharSequence): Maybe<List<Youtube>> {
        return getAll().map {
            it.filter { item -> item.title.contains(query, ignoreCase = true) }
        }
    }

    fun getBookmarked(): Maybe<List<Youtube>> {
        return getAll().map { it.filter { item -> item.isBookmarked } }
    }
}