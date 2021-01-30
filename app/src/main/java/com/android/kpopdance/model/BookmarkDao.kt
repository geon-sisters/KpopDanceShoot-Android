package com.android.kpopdance.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM Bookmark")
    fun getAll(): List<Bookmark>

    @Insert(onConflict = REPLACE)
    fun insert(bookmark: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)
}