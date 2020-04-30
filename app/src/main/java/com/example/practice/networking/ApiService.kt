package com.example.practice.networking

import com.example.practice.entity.CategoryModel
import com.example.practice.entity.DiscoverPostData
import com.example.practice.entity.DiscoverPostModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories_list_tcl")
    fun getAllCategories(): Observable<List<CategoryModel>>

    @GET("discover_tcl")
    fun getPostByCategory(
        @Query("page_no") page_no: Int,
        @Query("category_id") category_id: String,
        @Query("page_size") page_size: Int
    ): Observable<DiscoverPostModel>
}