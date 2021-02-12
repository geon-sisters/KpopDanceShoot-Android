package com.your.kpopdance.repository

import android.util.Log
import com.your.kpopdance.contract.Contract
import com.your.kpopdance.model.Bookmark
import com.your.kpopdance.model.BookmarkDao

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {
    private val TAG = Contract.YOUR_KDANCE + BookmarkRepository::class.simpleName

    fun insert(youtubeId: String) {
        Thread(Runnable {
            bookmarkDao.insert(Bookmark(youtubeId))
            Log.d(TAG, "Insert: $youtubeId")
        }).start()
    }

    fun delete(youtubeId: String) {
        Thread(Runnable {
            bookmarkDao.delete(Bookmark(youtubeId))
            Log.d(TAG, "Delete: $youtubeId")
        }).start()
    }
}