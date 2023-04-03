package com.example.seefood.screens.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seefood.common.composable.AreYouSureDialog

@Composable
fun CatalogScreen(
   viewModel: CatalogViewModel = hiltViewModel(),
   catalogName: String
){
   val dishes = viewModel.getCatalogsDishesListFlow(catalogName).collectAsState(initial = listOf())

   Column(modifier = Modifier.fillMaxSize())
   {
      Text(text = catalogName, color = Color.White)

      LazyColumn(modifier = Modifier.fillMaxSize()) {
         items(dishes.value.size) { dishIdx ->
            var isDialogVisible by remember { mutableStateOf(false) }

            Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween
            ) {
               Text(text = "${dishes.value[dishIdx].name} : ${dishes.value[dishIdx].catalog}", color = Color.White)
               Button(onClick = { isDialogVisible = true }) {
                  Text(text = "Remove")
               }
               if (isDialogVisible) {
                  AreYouSureDialog(
                     onDismiss = { isDialogVisible = false },
                     onConfirm = {
                        viewModel.removeDishFromCatalog(dishes.value[dishIdx]); isDialogVisible =
                        false
                     },
                     titleText = "Убрать ${dishes.value[dishIdx].name} из ${catalogName}?"
                  )
               }
            }
         }
      }
   }
}