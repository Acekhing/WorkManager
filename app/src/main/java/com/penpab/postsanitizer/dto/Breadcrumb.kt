package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class Breadcrumb(
    @SerializedName("@id")
    val id: String?
)