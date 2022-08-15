package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class MainEntityOfPage(
    @SerializedName("@id")
    val id: String?
)