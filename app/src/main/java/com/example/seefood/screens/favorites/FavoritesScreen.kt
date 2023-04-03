package com.example.seefood.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.common.composable.AreYouSureDialog
import com.example.seefood.database.objects.Dish

@Composable
fun FavoritesScreen(
   viewModel: FavoritesViewModel = hiltViewModel()
) {
   val dishesListState = viewModel.dishesListFlow.collectAsState(initial = listOf())

   var idCount by remember { mutableStateOf(0) }

   // Этот блок для тестирования TODO: Убрать потом!
   Column(
      modifier = Modifier
         .fillMaxWidth()
         .padding(10.dp),
   ){
      Column(
         modifier = Modifier.fillMaxWidth(),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Button(
            onClick = {
               viewModel.addDish(
                  Dish(
                     name = "fake_dish",
                     recipe = "fake_recipe",
                     imgLocalPath = "none",
                     category = "fake_category",
                     isFavorite = false,

                     id = idCount
                  )
               )
               idCount++
            }
         ) {
            Text(text = "add not favorited")
         }

         Button(
            onClick = {
               viewModel.addDish(
                  Dish(
                     name = "favorite_dish",
                     recipe = "fake_recipe",
                     imgLocalPath = "none",
                     category = "fake_category",
                     isFavorite = true,

                     id = idCount
                  )
               )
               idCount++
            }
         ) {
            Text(text = "add favorite")
         }
      }

      LazyColumn(
         modifier = Modifier.fillMaxSize()
      ){
         items(dishesListState.value.size) { dishIdx ->
            var isDialogVisible by remember { mutableStateOf(false) }

            Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween
            ) {
               Text(text = "${dishesListState.value[dishIdx].name} : ${dishesListState.value[dishIdx].id }", color = Color.White)
               Button(onClick = { isDialogVisible = true }) {
                  Text(text = "Unfavorite")
               }
               if (isDialogVisible) {
                  AreYouSureDialog(
                     onConfirm = { viewModel.unfavoriteDish(dishesListState.value[dishIdx]); isDialogVisible = false },
                     onDismiss = { isDialogVisible = false },
                     titleText = "Убрать ${dishesListState.value[dishIdx].name} из избранного?"
                  )
               }
            }
         }
      }
   }
}