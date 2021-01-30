package com.android.kpopdance.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.kpopdance.model.AppDatabase.Companion.DB_VERSION


@Database(entities = [Bookmark::class], version = DB_VERSION)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getBookmarkDao(): BookmarkDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "bookmark.db"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .build()
    }
}