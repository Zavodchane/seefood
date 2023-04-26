package com.example.seefood.data.camera

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

/**
 * Интерфейс для доступа к функциям камеры
 */
interface CameraService {
   /**
    * Функция фотографирования и сохранения картинки на устройстве
    *
    * @param[context] контекст
    */
   suspend fun captureAndSaveImage(context: Context)

   /**
    * Функция отображения окна предпросмотра камеры
    *
    * @param[previewView]
    * @param[lifecycleOwner]
    */
   suspend fun showCameraPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
}