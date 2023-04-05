package com.example.seefood.screens.catalogs_menu.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import com.example.seefood.CATALOG_SCREEN
import com.example.seefood.database.objects.Catalog
import java.io.File

@Composable
fun CatalogCard(
   catalog    : Catalog,
   openScreen : (String) -> Unit
) {
   // 0.21f для картинки, рассчитал по пикселям из фигмы
   
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .background(color = Color.Transparent)
         .clickable { openScreen("$CATALOG_SCREEN/${catalog.name}") },
      verticalAlignment = Alignment.CenterVertically
   ) {
      // TODO: Все паддинги, размеры, стили добавить в константы
      var imageSize by remember { mutableStateOf(Size.Zero) }
      AsyncImage(
         modifier = Modifier
            .fillMaxWidth(0.21f)
            .padding(horizontal = 30.dp, vertical = 25.dp)
            .onGloballyPositioned { coordinates -> imageSize = coordinates.size.toSize() }
            .height(with(LocalDensity.current) { imageSize.width.toDp() }),
         model = File(catalog.thumbnailLocalPath),
         contentDescription = "Каталог ${catalog.name}",
         contentScale = ContentScale.Crop
      )

      Column {
         Text(
            text = catalog.name,
            style = TextStyle(
               fontWeight = FontWeight.W500,
               fontSize = 22.sp,
               color = Color.White
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
         )
         Text(
            text = catalog.creationDate,
            style = TextStyle(
               fontWeight = FontWeight.W500,
               fontSize = 18.sp,
               color = Color.White
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
         )
      }
   }
}