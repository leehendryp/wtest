package com.leehendryp.wtest.data.entities

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("author") val author: String?,
    @SerializedName("body") val body: String?,
    @SerializedName("hero") val hero: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("limit") val limit: String?,
    @SerializedName("page") val page: String?,
    @SerializedName("published-at") val publishedAt: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("title") val title: String?
)
