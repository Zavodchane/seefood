package com.example.seefood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class SeeFoodActivity : ComponentActivity()
{
   override fun onCreate(savedInstanceState: Bundle?)
   {
      super.onCreate(savedInstanceState)
      setContent { SeeFoodApp() }
   }
}
