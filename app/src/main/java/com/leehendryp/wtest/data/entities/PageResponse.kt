package com.leehendryp.wtest.data.entities

import com.google.gson.annotations.SerializedName

data class PageResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("items") val articleResponseList: List<ArticleResponse>?
)