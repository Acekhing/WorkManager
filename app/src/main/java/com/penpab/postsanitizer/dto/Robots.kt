package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class Robots(
    val follow: String?,
    val index: String?,
    @SerializedName("max-image-preview")
    val maxImagePreview: String?,
    @SerializedName("max-snippet")
    val maxSnippet: String?,
    @SerializedName("max-video-preview")
    val maxVideoPreview: String?
)