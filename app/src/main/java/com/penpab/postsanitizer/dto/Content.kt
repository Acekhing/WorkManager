package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("protected")
    val protected: Boolean?,
    val rendered: String?
)