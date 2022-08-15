package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class Target(
    @SerializedName("type")
    val type: String?,
    val urlTemplate: String?
)