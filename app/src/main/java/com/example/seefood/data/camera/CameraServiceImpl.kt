package com.example.seefood.data.camera

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.seefood.common.util.URIPathHelper
import java.util.*

class CameraServiceImpl (
   private val cameraProvider: ProcessCameraProvider,
   private val selector: CameraSelector,
   private val preview: Preview,
//   private val imageAnalysis: ImageAnalysis,
   private val imageCapture: ImageCapture
): CameraService {

   @SuppressLint("SimpleDateFormat")
   override suspend fun captureAndSaveImage(context: Context) {
      val name = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.ENGLISH).format(System.currentTimeMillis())

      val contentValues = ContentValues().apply {
         put(MediaStore.MediaColumns.DISPLAY_NAME,name)
         put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
         if (Build.VERSION.SDK_INT > 28){
            put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/SeeFood")
         }
      }

      val outputOptions = ImageCapture.OutputFileOptions
         .Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
         )
         .build()

      imageCapture.takePicture(
         outputOptions,
         ContextCompat.getMainExecutor(context),
         object : ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
               val helper = URIPathHelper()
               val path = helper.getPath(context, outputFileResults.savedUri!!)

               Toast.makeText(
                  context,
                  "Saved image $path",
                  Toast.LENGTH_SHORT
               ).show()
            }

            override fun onError(exception: ImageCaptureException) {
               Toast.makeText(
                  context,
                  "Error: ${exception.message}",
                  Toast.LENGTH_SHORT
               ).show()
            }
         }
      )
   }

   override suspend fun showCameraPreview(
      previewView: PreviewView,
      lifecycleOwner: LifecycleOwner
   ) {
      preview.setSurfaceProvider(previewView.surfaceProvider)
      try {
         cameraProvider.unbindAll()
         cameraProvider.bindToLifecycle(
            lifecycleOwner,
            selector,
            preview,
//            imageAnalysis,
            imageCapture
         )
      }catch (e:Exception){
         e.printStackTrace()
      }
   }
}