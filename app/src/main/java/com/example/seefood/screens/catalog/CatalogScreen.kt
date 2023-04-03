package com.example.seefood.screens.catalog

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatalogScreen(
   viewModel: CatalogViewModel = hiltViewModel(),
   catalogName: String
){
   val dishes = viewModel.getCatalogsDishesListFlow(catalogName).collectAsState(initial = listOf())
}