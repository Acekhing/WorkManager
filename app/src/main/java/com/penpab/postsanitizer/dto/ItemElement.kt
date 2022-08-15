package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class ItemElement(
    @SerializedName("@type")
    val type: String?,
    val item: String?,
    val name: String?,
    val position: Int?
)