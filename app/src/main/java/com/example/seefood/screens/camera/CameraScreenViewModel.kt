package com.example.seefood.screens.camera

import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import java.io.File

class CameraScreenViewModel : ViewModel() {

   var imageUri = mutableStateOf(EMPTY_IMAGE_URI)
      private set

   fun onTakePhoto(file: File) { imageUri.value = file.toUri() }

   fun onSend() {
      // TODO: Дописать функцию отправки

      imageUri.value = EMPTY_IMAGE_URI // Это строго после функции отправки!!!

   }
}