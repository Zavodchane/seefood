package com.example.seefood.screens.home

import androidx.lifecycle.ViewModel
import com.example.seefood.CAMERA_SCREEN
import com.example.seefood.CATALOG_MENU_SCREEN
import com.example.seefood.FAVORITES_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface HomeScreenViewModelAbstract {
    fun onFavoritesPressed (openScreen: (String) -> Unit)
    fun onCatalogPressed   (openScreen: (String) -> Unit)
    fun onCameraPressed    (openScreen: (String) -> Unit)
}

@HiltViewModel
class HomeScreenViewModel
@Inject constructor() : ViewModel(), HomeScreenViewModelAbstract {
    override fun onFavoritesPressed(openScreen: (String) -> Unit) {
        openScreen(FAVORITES_SCREEN)
    }

    override fun onCatalogPressed(openScreen: (String) -> Unit) {
        openScreen(CATALOG_MENU_SCREEN)
    }

    override fun onCameraPressed(openScreen: (String) -> Unit) {
        openScreen(CAMERA_SCREEN)
    }
}