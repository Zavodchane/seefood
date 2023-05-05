package com.example.seefood.data.network

import javax.inject.Inject

class ApiServiceImpl @Inject constructor( private val apiService: ApiService ) {

   suspend fun sendFile() = apiService.sendFile()

//   suspend fun getClassificationResult() = apiService.getClassificationResult()
}