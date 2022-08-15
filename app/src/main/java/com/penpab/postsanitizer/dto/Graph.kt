package com.penpab.postsanitizer.dto

import com.google.gson.annotations.SerializedName

data class Graph(
    @SerializedName("@id")
    val id: String?,
    @SerializedName("@type")
    val type: String?,
    val articleSection: List<String>?,
    val author: AuthorX?,
    val breadcrumb: Breadcrumb?,
    val commentCount: Int?,
    val contentUrl: String?,
    val dateModified: String?,
    val datePublished: String?,
    val description: String?,
    val headline: String?,
    val height: Int?,
    val image: Image?,
    val inLanguage: String?,
    val isPartOf: IsPartOf?,
    val itemListElement: List<ItemElement>?,
    val logo: Logo?,
    val mainEntityOfPage: MainEntityOfPage?,
    val name: String?,
    val potentialAction: List<PotentialAction>?,
    val primaryImageOfPage: PrimaryImageOfPage?,
    val publisher: Publisher?,
    val sameAs: List<String>?,
    val thumbnailUrl: String?,
    val url: String?,
    val width: Int?,
    val wordCount: Int?
)