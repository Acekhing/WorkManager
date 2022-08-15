package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class PotentialAction(
    @SerializedName("@type")
    val type: String?,
    val name: String?,
    @SerializedName("query-input")
    val queryInput: String?,
    val target: Target?
)