package com.example.seefood.data.network

import okhttp3.MultipartBody
import javax.inject.Inject

class ApiServiceImpl @Inject constructor( private val apiService: ApiService ) {
   suspend fun sendImageToClassifier(photo : MultipartBody.Part) = apiService.sendImageToClassifier(photo)
}