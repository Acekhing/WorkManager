package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class PrimaryImageOfPage(
    @SerializedName("@id")
    val id: String?
)