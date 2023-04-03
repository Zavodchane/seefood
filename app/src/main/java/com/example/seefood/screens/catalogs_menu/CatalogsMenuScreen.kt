package com.example.seefood.screens.catalogs_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.CATALOG_SCREEN

@Composable
fun CatalogsMenuScreen(
   viewModel  : CatalogsMenuViewModel = hiltViewModel(),
   openScreen : (String) -> Unit
){
   val catalogsListState = viewModel.catalogsListFlow.collectAsState(initial = listOf())
   
   LazyColumn(modifier = Modifier.fillMaxWidth()) {
      items(catalogsListState.value.size) { catalogIdx ->
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
         ) {
            Text(text = catalogsListState.value[catalogIdx].name, color = Color.White)
            Button(onClick = { openScreen("$CATALOG_SCREEN/" + catalogsListState.value[catalogIdx].name) }) {
               Text(text = "open catalog")
            }
         }
      }
   }
}