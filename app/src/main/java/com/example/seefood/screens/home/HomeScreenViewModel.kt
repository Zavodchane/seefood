package com.example.seefood.screens.home

import androidx.lifecycle.ViewModel
import com.example.seefood.CAMERA_SCREEN
import com.example.seefood.CATALOG_MENU_SCREEN
import com.example.seefood.FAVORITES_SCREEN

class HomeScreenViewModel : ViewModel (){
    fun onFavoritesPressed(openScreen: (String) -> Unit){
        openScreen(FAVORITES_SCREEN)
    }

    fun onCatalogPressed(openScreen: (String) -> Unit){
        openScreen(CATALOG_MENU_SCREEN)
    }

    fun onCameraPressed(openScreen: (String) -> Unit){
        openScreen(CAMERA_SCREEN)
    }
}