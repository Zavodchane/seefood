package com.example.seefood.di

import android.app.Application
import androidx.camera.core.*
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture.FLASH_MODE_AUTO
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.seefood.data.camera.CameraService
import com.example.seefood.data.camera.CameraServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SeeFoodCameraModule {

   @Provides
   @Singleton
   fun provideCameraSelector(): CameraSelector {
      return CameraSelector.Builder()
         .requireLensFacing(CameraSelector.LENS_FACING_BACK)
         .build()
   }

   @Provides
   @Singleton
   fun provideCameraProvider(application: Application)
         : ProcessCameraProvider {
      return ProcessCameraProvider.getInstance(application).get()

   }

   @Provides
   @Singleton
   fun provideCameraPreview(): Preview {
      return Preview.Builder().build()
   }

   @Provides
   @Singleton
   fun provideImageCapture(): ImageCapture {
      return ImageCapture.Builder()
         .setFlashMode(FLASH_MODE_AUTO)
         .build()
   }

   @Provides
   @Singleton
   fun provideImageAnalysis(): ImageAnalysis {
      return ImageAnalysis.Builder()
         .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
         .build()
   }

   @Provides
   @Singleton
   fun provideCustomCameraRepo(
      cameraProvider: ProcessCameraProvider,
      selector: CameraSelector,
      imageCapture: ImageCapture,
      preview: Preview
   ): CameraService {
      return CameraServiceImpl (
         cameraProvider,
         selector,
         preview,
         imageCapture
      )
   }
}