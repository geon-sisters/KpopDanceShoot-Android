package com.android.kpopdance.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark (
    @PrimaryKey val youtubeId: String
)