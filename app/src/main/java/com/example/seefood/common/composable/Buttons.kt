package com.example.seefood.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun MenuButton(
  @DrawableRes buttonSkin: Int,
  modifier: Modifier = Modifier,
  contentDescription: String,
  onClick: () -> Unit
) {
  val interactionSource = MutableInteractionSource()

  Row(
    modifier = modifier
      .clickable (
        interactionSource = interactionSource,
        indication = null
      ) {
        onClick()
      },
  ) {
    AsyncImage(model = buttonSkin, contentDescription = contentDescription)
  }
}