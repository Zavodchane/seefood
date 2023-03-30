package com.example.seefood.screens.camera

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CameraScreenViewModel : ViewModel() {
   var imageUri = mutableStateOf(EMPTY_IMAGE_URI)
      private set
}