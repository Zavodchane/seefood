package com.example.seefood.screens.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun CameraScreen(
    goBack: () -> Unit,
    viewModel: CameraScreenViewModel = viewModel()
) {
    RequestCameraPermissions()
}

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