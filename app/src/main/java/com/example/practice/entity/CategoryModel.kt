package com.example.practice.entity

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    val _id: String,
    val names: List<CategoryName>,
    val thumbnail: String
)

data class CategoryName(
    @SerializedName("_id")
    val categoryId: String,
    val language: String,
    val translation: String
)