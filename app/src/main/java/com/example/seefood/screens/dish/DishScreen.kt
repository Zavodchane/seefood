package com.example.seefood.screens.dish

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.R
import com.example.seefood.database.objects.Dish
import com.example.seefood.ui.theme.Accent
import kotlin.math.roundToInt

@Composable
fun DishScreen(
   viewModel: DishViewModel = hiltViewModel(),
   dishId: Int
) {
   val relatedDish = viewModel.getRelatedDish(dishId = dishId).collectAsState(initial = Dish())

   val max = 420.dp
   val min = 0.dp

   val (minPx, maxPx) = with(LocalDensity.current) { min.toPx() to max.toPx() }
   val offset = remember { mutableStateOf(maxPx) }

   var rowSize by remember { mutableStateOf(Size.Zero) }

   Image( // TODO: Поменять на AsyncImage c путем к картинке из relatedDish (ниже)
      modifier= Modifier
         .height(max)
         .fillMaxWidth()
         .background(Color.White)
         .padding(20.dp),
      painter = painterResource(id = R.drawable.food_mock),
      contentDescription = null,
      contentScale = ContentScale.Fit // TODO: ContentScale.Crop
   )

//   var imageSize by remember { mutableStateOf(Size.Zero) }
//   val helper = URIPathHelper()
//   val imageFile = File(helper.getPath(LocalContext.current, Uri.parse(relatedDish.value.imgLocalPath)).toString())
//
//   AsyncImage(
//      modifier = Modifier
//         .fillMaxWidth(0.21f)
//         .clip(RoundedCornerShape(10.dp))
//         .onGloballyPositioned { coordinates -> imageSize = coordinates.size.toSize() }
//         .height(with(LocalDensity.current) { imageSize.width.toDp() }),
//      model = imageFile,
//      contentDescription = relatedDish.value.name,
//      contentScale = ContentScale.Crop
//   )

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
            Text(
               text = relatedDish.value.name,
               style = TextStyle(color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.W700)
            )

            LazyColumn {
               item { 
                  Text(
                     text = relatedDish.value.recipe,
                     style = TextStyle(color = Color.White, fontSize = 18.sp)
                  )
               }
            }

         }
      }
   }
}