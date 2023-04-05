package com.example.seefood.screens.catalogs_menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.screens.catalogs_menu.composable.CatalogCard

@Composable
fun CatalogsMenuScreen(
   viewModel  : CatalogsMenuViewModel = hiltViewModel(),
   openScreen : (String) -> Unit
){
   val catalogsListState = viewModel.catalogsListFlow.collectAsState(initial = listOf())

   var colSize by remember { mutableStateOf(Size.Zero) }


   // TODO: Добавить в константы все паддинги и цвета, может создать ext Modifier'ы
   Column(
      modifier = Modifier
         .fillMaxSize()
         .onGloballyPositioned { coordinates ->
            colSize = coordinates.size.toSize()
         }
   ) {
      FloatingActionButton(
         modifier = Modifier
            .padding(top = 10.dp, end = 15.dp)
            .size(with(LocalDensity.current) { colSize.width.toDp() / 8f })
            .align(Alignment.End),
         onClick = { /*TODO*/ },
         backgroundColor = Color.Transparent
      ) {
         Icon(
            modifier = Modifier
               .fillMaxSize(),
            imageVector = Icons.Rounded.AddCircle,
            contentDescription = "Добавить каталог",
            tint = Color.White
         )
      }
   }

   LazyColumn(
      modifier = Modifier
         .fillMaxSize()
         .padding(top = with(LocalDensity.current) { (colSize.width.toDp() / 8f) / 1.5f })
   ) {
      items(catalogsListState.value.size) { catalogIdx ->
         CatalogCard(catalog = catalogsListState.value[catalogIdx], openScreen = openScreen)
      }
   }
}