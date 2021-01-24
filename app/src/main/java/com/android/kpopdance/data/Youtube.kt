package com.android.kpopdance.data

import com.google.gson.annotations.SerializedName

data class Youtube (
    @SerializedName("id") var id: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("name") var name: String = "",
    var date: String = "",
    var isBookmarked: Boolean = false
)