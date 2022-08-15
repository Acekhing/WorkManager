package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class IsPartOf(
    @SerializedName("@id")
    val id: String?
)