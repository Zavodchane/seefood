package com.example.seefood.screens.catalog.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.seefood.DISH_SCREEN
import com.example.seefood.R
import com.example.seefood.database.objects.Dish

@Composable
fun DishCard(
   dish : Dish,
   openScreen : (String) -> Unit
){
   val universal = 100.dp

   Column(
      modifier = Modifier
         .padding(15.dp)
         .width(universal)
         .clickable {
            openScreen("$DISH_SCREEN/${dish.id}")
         },
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
//      val helper = URIPathHelper()
//      val imageFile = File(helper.getPath(LocalContext.current, Uri.parse(dish.imgLocalPath)).toString())

      AsyncImage(
         modifier = Modifier
            .width(universal)
            .clip(RoundedCornerShape(10.dp))
            .height(universal),
         model = R.drawable.food_mock, // imageFile // TODO: Потом переделать под картинку из пути
         contentDescription = dish.name,
         contentScale = ContentScale.Crop
      )

      Text(
         modifier = Modifier.padding(top = 10.dp),
         text = dish.name,
         style = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
         ),
         maxLines = 1,
         overflow = TextOverflow.Ellipsis
      )
   }
}