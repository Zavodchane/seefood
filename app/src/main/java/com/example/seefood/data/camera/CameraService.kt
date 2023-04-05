package com.example.seefood.data.camera

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraService {
   suspend fun captureAndSaveImage(context: Context)
   suspend fun showCameraPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
}