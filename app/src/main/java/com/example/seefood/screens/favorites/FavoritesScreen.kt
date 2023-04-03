package com.example.seefood.screens.favorites

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritesScreen(
   viewModel: FavoritesViewModel = hiltViewModel()
) {
   val dishesListState = viewModel.dishesListFlow.collectAsState(initial = listOf())
}