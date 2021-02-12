package com.your.kpopdance.repository

import android.util.Log
import com.your.kpopdance.contract.Contract
import com.your.kpopdance.data.Youtube
import com.your.kpopdance.model.Bookmark
import com.your.kpopdance.model.BookmarkDao
import com.your.kpopdance.model.YoutubeApi
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class YoutubeRepository(youtubeApi: YoutubeApi, private val bookmarkDao: BookmarkDao) {
    private val TAG = Contract.YOUR_KDANCE + YoutubeRepository::class.simpleName

    private var cachedYoutubes: List<Youtube> = arrayListOf()

    private var memory: Observable<List<Youtube>> = Observable.create { subscriber ->
        subscriber.onNext(cachedYoutubes)
        subscriber.onComplete()
    }

    private var remote: Observable<List<Youtube>> = youtubeApi.get()
        .subscribeOn(Schedulers.io())
        .map {
            val bookmarkId = bookmarkDao.getAll().map(Bookmark::youtubeId)
            it.map { youtube ->
                if (bookmarkId.contains(youtube.id)) {
                    youtube.isBookmarked = true
                }
                youtube.localDate = LocalDate.parse(youtube.date, DateTimeFormatter.ofPattern("yyyy. M. d."))
            }
            it.sortedByDescending(Youtube::localDate)
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

    fun getQuickSearch(): Maybe<List<String>> {
        return getAll().map{ it.map(Youtube::name) }
    }
}