package com.example.seefood.screens.dish

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.seefood.R
import com.example.seefood.database.objects.Dish
import com.example.seefood.ui.theme.Accent
import kotlin.math.roundToInt

/**
 * Экран блюда (UI представление)
 *
 * @param[viewModel] модель представления, предоставляется с помощью Hilt
 * @param[dishId] идентификатор блюда, которое нужно отобразить
 * @param[onDishDelete] функция которая вызывается при удалении блюда из локальной БД
 */
@Composable
fun DishScreen(
   viewModel: DishViewModel = hiltViewModel(),
   dishId: Int,
   onDishDelete : () -> Unit
) {
   val relatedDish = viewModel.getRelatedDish(dishId = dishId).collectAsState(initial = Dish())

   val max = LocalConfiguration.current.screenHeightDp.dp / 2
   val min = 0.dp

   val (minPx, maxPx) = with(LocalDensity.current) { min.toPx() to max.toPx() }
   val offset = remember { mutableStateOf(maxPx) }

   var rowSize by remember { mutableStateOf(Size.Zero) }

   var imageSize by remember { mutableStateOf(Size.Zero) }

//   val helper    = URIPathHelper()
//   val imageFile = File(helper.getPath(LocalContext.current, Uri.parse(relatedDish.value.imgLocalPath)).toString())

   Box(
      modifier = Modifier.background(color = Color.White)
   ) {
      AsyncImage(
         modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates -> imageSize = coordinates.size.toSize() }
            .height(max),
         model = R.drawable.food_mock, // imageFile // TODO: Заменить заглушку на нормальное фото блюда из пути
         contentDescription = relatedDish.value?.name,
         contentScale = ContentScale.Crop
      )

      IconButton(
         modifier = Modifier
            .padding(10.dp)
            .align(Alignment.BottomEnd),
         onClick = {
            onDishDelete()
            relatedDish.value?.let { viewModel.removeDishFromCatalog(it) }
         }
      ) {
         Icon(imageVector = Icons.Rounded.Delete, contentDescription = "убрать из каталога")
      }
   }

   Box(
      modifier = Modifier
         .fillMaxWidth()
         .fillMaxHeight()
         .offset { IntOffset(0, offset.value.roundToInt()) }
         .background(color = Color.Black)
   ) {
      Column() {
         Row(
            modifier = Modifier
               .fillMaxWidth()
               .height(20.dp)
               .onGloballyPositioned { coordinates ->
                  rowSize = coordinates.size.toSize()
               }
               .draggable(
                  orientation = Orientation.Vertical,
                  state = rememberDraggableState { delta ->
                     val updValue = offset.value + delta
                     offset.value = updValue.coerceIn(minPx, maxPx)
                  }
               ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top // Alignment.CenterVertically
         ) {
            Box(
               modifier = Modifier
                  .fillMaxWidth() // width(with(LocalDensity.current) { rowSize.width.toDp() / 4 })
                  .height(with(LocalDensity.current) { rowSize.height.toDp() / 4.5f })
                  .background(color = Accent, /*shape = RoundedCornerShape(with(LocalDensity.current) { rowSize.height.toDp() / 10 })*/)
            )
         }

         Column(
            modifier = Modifier.padding(horizontal = 20.dp)
         ){
            relatedDish.value?.let {
               Text(
                  text = it.name,
                  style = TextStyle(color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.W700)
               )
            }

            LazyColumn {
               item {
                  relatedDish.value?.let {
                     Text(
                        text = it.recipe,
                        style = TextStyle(color = Color.White, fontSize = 18.sp)
                     )
                  }
               }
            }
         }
      }
   }
}