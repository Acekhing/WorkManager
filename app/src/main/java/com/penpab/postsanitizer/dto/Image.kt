package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("@id")
    val id: String?,
    @SerializedName("@type")
    val type: String?,
    val caption: String?,
    val contentUrl: String?,
    val inLanguage: String?,
    val url: String?
)