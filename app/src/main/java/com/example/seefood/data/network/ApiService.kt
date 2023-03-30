package com.example.seefood.data.network

import com.example.seefood.data.models.ClassificationResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST // TODO: Прописать POST запрос
    suspend fun sendFile()

    @GET // TODO: Прописать GET запрос
    suspend fun getClassificationResult() : Response<ClassificationResult>
}