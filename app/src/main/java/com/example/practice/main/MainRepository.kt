package com.example.practice.main

import com.example.practice.networking.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(val apiService: ApiService){
    fun getAllCategory()=apiService.getAllCategories()

}