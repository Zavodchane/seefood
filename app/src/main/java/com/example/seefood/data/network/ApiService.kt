package com.example.seefood.data.network

import com.example.seefood.data.models.ClassificationResult
import retrofit2.Response
import retrofit2.http.POST

/**
 * Интерфейс для запросов к API
 */
interface ApiService {

   /**
    * POST запрос к API для классификации изображения
    */
   @POST // TODO: Прописать POST запрос и добавить параметр
   suspend fun sendFile() : Response<ClassificationResult>
}