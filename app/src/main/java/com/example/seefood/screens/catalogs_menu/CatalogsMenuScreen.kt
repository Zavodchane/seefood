package com.example.seefood.screens.catalogs_menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatalogsMenuScreen(
   viewModel  : CatalogsMenuViewModel = hiltViewModel(),
   openScreen : (String) -> Unit
){
   val catalogsListState = viewModel.catalogsListFlow.collectAsState(initial = listOf())
}