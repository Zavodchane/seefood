package com.example.seefood.screens.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.screens.catalog.composable.DishCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CatalogScreen(
   viewModel: CatalogViewModel = hiltViewModel(),
   catalogName: String,
   openScreen: (String) -> Unit
){
   val dishes = viewModel.getCatalogsDishesListFlow(catalogName).collectAsState(initial = listOf())

   FlowRow(
      modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      dishes.value.forEach { dish ->
         DishCard(dish = dish, openScreen = openScreen)
      }
   }
}