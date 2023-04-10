package com.example.seefood.screens.dish

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.database.objects.Dish

@Composable
fun DishScreen(
   viewModel: DishViewModel = hiltViewModel(),
   dishId: Int
) {
   val relatedDish = viewModel.getRelatedDish(dishId = dishId).collectAsState(initial = Dish())

   Text(
      text = relatedDish.value.name,
      color = Color.White
   )
}