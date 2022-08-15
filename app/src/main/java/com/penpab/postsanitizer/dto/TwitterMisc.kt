package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class TwitterMisc(
    @SerializedName("Est. reading time")
    val EstimatedReadingTime: String?,
    @SerializedName("Written by")
    val WrittenBy: String?
)