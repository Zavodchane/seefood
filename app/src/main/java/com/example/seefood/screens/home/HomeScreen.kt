package com.example.seefood.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.seefood.R
import com.example.seefood.common.composable.MenuButton

@Composable
fun HomeScreen(
  // TODO: Create a viewmodel
){
  Column(
    modifier = Modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.SpaceBetween
  ) {

    MenuButton(
      modifier = Modifier.align(Alignment.End).fillMaxWidth(0.5f),
      buttonSkin = R.drawable.favorites,
      contentDescription = "Favorites",
      onClick = { println("Clicked on favorites") }
    )

    MenuButton(
      modifier = Modifier.align(Alignment.Start).fillMaxWidth(0.5f),
      buttonSkin = R.drawable.catalogs,
      contentDescription = "Catalogs",
      onClick = { println("Clicked on catalogs") }
    )

    MenuButton(
      modifier = Modifier.fillMaxWidth(),
      buttonSkin = R.drawable.camera,
      contentDescription = "Camera",
      onClick = { println("Clicked on camera") }
    )

  }
}