package com.example.seefood.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.seefood.R.drawable as AppImages
import com.example.seefood.common.composable.MenuButton
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
  openScreen: (String) -> Unit,
  viewModel: HomeScreenViewModel = viewModel()
){
  Column(modifier = Modifier.fillMaxSize()) {
    Spacer(modifier = Modifier.weight(0.3f))
    MenuButton( // Кнопка избранного
      modifier = Modifier
        .align(Alignment.End)
        .fillMaxWidth(0.55f),
      buttonSkin = AppImages.favorites,
      contentDescription = "Favorites",
      onClick = { viewModel.onFavoritesPressed(openScreen) }
    )
    Spacer(modifier = Modifier.weight(0.5f))
    MenuButton( // Кнопка каталогов
      modifier = Modifier
        .align(Alignment.Start)
        .fillMaxWidth(0.55f),
      buttonSkin = AppImages.catalogs,
      contentDescription = "Catalogs",
      onClick = { viewModel.onCatalogPressed(openScreen) }
    )
    Spacer(modifier = Modifier.weight(0.9f))
    MenuButton( // Кнопка камеры
      modifier = Modifier.fillMaxWidth(),
      buttonSkin = AppImages.camera,
      contentDescription = "Camera",
      onClick = { viewModel.onCameraPressed(openScreen) }
    )
  }
}