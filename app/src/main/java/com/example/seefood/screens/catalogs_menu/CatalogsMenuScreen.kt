package com.example.seefood.screens.catalogs_menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
import com.example.seefood.database.objects.Catalog
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
            .offset(y = 10.dp, x = (-15).dp)
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

   // Для теста
   viewModel.addCatalog(
      Catalog(
         name = "Exampleur",
         creationDate = "0",
         thumbnailLocalPath = "/storage/emulated/0/Pictures/SeeFood/2023-04-05-22-28-26-444.jpg"
      )
   )

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