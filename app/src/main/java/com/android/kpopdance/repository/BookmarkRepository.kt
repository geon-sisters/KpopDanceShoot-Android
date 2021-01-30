package com.android.kpopdance.repository

import android.util.Log
import com.android.kpopdance.contract.Contract
import com.android.kpopdance.model.Bookmark
import com.android.kpopdance.model.BookmarkDao

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {
    private val TAG = Contract.K_POP_DANCE + BookmarkRepository::class.simpleName

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