package com.example.seefood.screens.catalogs_menu

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.R
import com.example.seefood.database.objects.Catalog
import com.example.seefood.screens.catalogs_menu.composable.CatalogCard
import com.example.seefood.ui.theme.Background

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
      val showImagePicker = remember { mutableStateOf(false) }
      
      FloatingActionButton(
         modifier = Modifier
            .size(with(LocalDensity.current) { colSize.width.toDp() / 8f })
            .align(Alignment.End),
         onClick = { showImagePicker.value = true },
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
      
      if (showImagePicker.value) {
         AlertDialog(
            modifier = Modifier.clip(RoundedCornerShape(20.dp)),
            backgroundColor = Background,
            contentColor = Color.White,
            onDismissRequest = { showImagePicker.value = false },
            title = {
               Text(
                  text = "Новый каталог",
                  style = TextStyle(
                     fontWeight = FontWeight.W500,
                     fontSize = 22.sp,
                     color = Color.White
                  )
               )
            },
            text = {
               Column(){
                  Text(text = "Тестовый текст в диалоге")

                  var imageUri by remember { mutableStateOf<Uri?>(null) }
                  val launcher = rememberLauncherForActivityResult(
                     contract = ActivityResultContracts.GetContent()
                  ) { uri: Uri? ->
                     imageUri = uri
                  }

                  Button(onClick = { launcher.launch("image/jpeg") }) {
                     Text(text = "Обложка")
                  }
                  Button(
                     enabled = imageUri != null,
                     onClick = {
                        viewModel.addCatalog(
                           Catalog(
                              name = "TestName",
                              creationDate = "today",
                              thumbnailLocalPath = imageUri.toString()
                           )
                        )
                        showImagePicker.value = false
                     }
                  ) {
                     Text(text = "Добавить")
                  }
               }
            },
            buttons = {}
         )
      }
   }

   LazyColumn(
      modifier = Modifier
         .fillMaxSize()
         .padding(top = with(LocalDensity.current) { (colSize.width.toDp() / 8f) / 1.5f }),
      contentPadding = PaddingValues(start = 30.dp, end = with (LocalDensity.current) { colSize.width.toDp() / 8f } )
   ) {
      items(catalogsListState.value.size) { catalogIdx ->
         CatalogCard(catalog = catalogsListState.value[catalogIdx], openScreen = openScreen)
      }
   }
}