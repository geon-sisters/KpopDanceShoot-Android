package com.your.kpopdance.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Youtube (
    @SerializedName("id") var id: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("date") var date: String = "",
    var isBookmarked: Boolean = false,
    var localDate: LocalDate = LocalDate.of(2013, 1, 1)
)