package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class Publisher(
    @SerializedName("@id")
    val id: String?
)