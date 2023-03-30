package com.example.seefood.screens.camera

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.seefood.SeeFoodAppState
import com.example.seefood.ui.theme.ButtonBackground
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

// TODO: Переделать под Clean Architecture (частично готово) и вообще навести порядок
@Composable
fun CameraScreen(
   appState: SeeFoodAppState,
   viewModel: CameraScreenViewModel = viewModel()
) {
   val imageUri by viewModel.imageUri

   if (!uriEmpty(imageUri)){
      Box(
         modifier = Modifier.fillMaxSize(),
         contentAlignment = Alignment.Center
      ) {
         AsyncImage(model = imageUri, contentDescription = null)
         Button(
            modifier = Modifier
               .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBackground),
            onClick = { viewModel.onSend() }
         ) {
            Text("Отправить", color = Color.White)
         }
      }
   }
   else {
      CameraCapture(
         modifier = Modifier.fillMaxSize(),
         appState = appState
      ) { file ->
         viewModel.onTakePhoto(file)
      }
   }
}

private fun uriEmpty(uri: Uri) : Boolean {
   return uri == EMPTY_IMAGE_URI
}

/**
 * Запрос разрешения для использования камеры
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestCameraPermissions(
   permission: String = android.Manifest.permission.CAMERA,
){
   val cameraPermissionState = rememberPermissionState(permission = permission)
   LaunchedEffect(true){
      cameraPermissionState.launchPermissionRequest()
   }
}


// TODO: Переместить в соответствующую папку и возможно как-то зарефакторить
suspend fun Context.getCameraProvider() : ProcessCameraProvider = suspendCoroutine { continuation ->
   ProcessCameraProvider.getInstance(this).also { future ->
      future.addListener({
         continuation.resume(future.run { get() })
      }, executor)
   }
}

val Context.executor : Executor get() = ContextCompat.getMainExecutor(this)

// TODO: Переделать под Clean Architecture и вообще навести порядок
@Composable
fun CameraPreview(
   modifier: Modifier = Modifier,
   scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
   onUseCase: (UseCase) -> Unit,
){
   AndroidView(
      modifier = modifier,
      factory = { context ->
         val previewView = PreviewView(context).apply {
            this.scaleType = scaleType
            layoutParams = ViewGroup.LayoutParams(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.MATCH_PARENT
            )
         }

         onUseCase(
            Preview.Builder()
               .build()
               .also{
                  it.setSurfaceProvider(previewView.surfaceProvider)
               }
         )

         previewView
      }
   )
}

// TODO: Переделать под Clean Architecture и вообще навести порядок
@Composable
fun CameraCapture(
   modifier: Modifier = Modifier,
   cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
   appState: SeeFoodAppState,
   onImageFile: (File) -> Unit = { }
) {
   RequestCameraPermissions()

   Box(modifier = modifier) {
      val lifecycleOwner = LocalLifecycleOwner.current
      var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
      val imageCaptureUseCase by remember {
         mutableStateOf(
            ImageCapture.Builder()
               .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
               .build()
         )
      }
      Box {
         CameraPreview(
            modifier = Modifier.fillMaxSize(),
            onUseCase = {
               previewUseCase = it
            }
         )
         Row(
            modifier = Modifier
               .fillMaxWidth()
               .background(color = Color.Black)
               .padding(vertical = 20.dp)
               .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
         ){
            CameraButton(
               context = appState.context,
               coroutineScope = appState.coroutineScope,
               imageCaptureUseCase = imageCaptureUseCase,
               onImageFile = onImageFile
            )
         }
      }
      LaunchedEffect(previewUseCase) {
         val cameraProvider = appState.context.getCameraProvider()
         try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
               lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
            )
         } catch (ex: Exception) {
            Log.e("CameraCapture", "Failed to bind camera use cases", ex)
         }
      }
   }
}

@Composable
fun CameraButton(
   coroutineScope: CoroutineScope,
   context: Context,
   imageCaptureUseCase: ImageCapture,
   onImageFile: (File) -> Unit
){
   var buttonColor by remember { mutableStateOf(Color.LightGray) }

   Box(
      modifier = Modifier
         .size(70.dp)
         .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
         .padding(5.dp)
         .clip(CircleShape)
         .background(color = buttonColor)
         .clickable {
            coroutineScope.launch {
               buttonColor = Color.Gray
               imageCaptureUseCase
                  .takePicture(context.executor)
                  .let {
                     onImageFile(it)
                  }
            }
         }
   )
}


// TODO: Поместить в соответствующую папку, и вообще разобраться с этой функцией
suspend fun ImageCapture.takePicture(executor: Executor): File {
   val photoFile = withContext(Dispatchers.IO) {
      kotlin.runCatching {
         File.createTempFile("image", "jpg")
      }.getOrElse { ex ->
         Log.e("TakePicture", "Failed to create temporary file", ex)
         File("/dev/null")
      }
   }

   return suspendCoroutine { continuation ->
      val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
      takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
         override fun onImageSaved(output: ImageCapture.OutputFileResults) {
            continuation.resume(photoFile)
         }
         override fun onError(ex: ImageCaptureException) {
            Log.e("TakePicture", "Image capture failed", ex)
            continuation.resumeWithException(ex)
         }
      })
   }
}