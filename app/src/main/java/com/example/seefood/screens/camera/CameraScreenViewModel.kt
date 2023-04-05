package com.example.seefood.screens.camera

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seefood.data.camera.CameraService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraScreenViewModel @Inject constructor(
   private val repo: CameraService
) : ViewModel() {
   fun showCameraPreview(
      previewView: PreviewView,
      lifecycleOwner: LifecycleOwner
   ){
      viewModelScope.launch {
         repo.showCameraPreview(
            previewView,
            lifecycleOwner
         )
      }
   }

   fun captureAndSave(context: Context){
      viewModelScope.launch {
         repo.captureAndSaveImage(context)
      }
   }
}