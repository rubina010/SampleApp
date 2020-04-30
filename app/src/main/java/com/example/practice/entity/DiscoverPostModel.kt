package com.example.practice.entity

data class DiscoverPostModel(
    val message: String,
    val data: DiscoverPostData
)

data class DiscoverPostData(
    val count: Long,
    val page_no: Long,
    val max_page: Long,
    val posts: List<DiscoverPostList>?
)

data class DiscoverPostList(
    val type: String,
    val title: String,
    val description: String,
    val media: String,
    val created_at: String,
    val deep_link_url:String
)