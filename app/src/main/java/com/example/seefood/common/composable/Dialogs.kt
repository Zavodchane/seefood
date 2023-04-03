package com.example.seefood.common.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


// TODO: Допилить в соответствии с дизайном
@Composable
fun AreYouSureDialog(
   onDismiss: () -> Unit,
   onConfirm: () -> Unit,
   titleText: String
) {
   AlertDialog(
      onDismissRequest = onDismiss,
      title = { Text(text = titleText) },
      confirmButton = {
         Button( onClick = onConfirm ) {
            Text(text = "Да")
         }
      },
      dismissButton = {
         Button(onClick = onDismiss) {
            Text(text = "Нет")
         }
      }
   )
}