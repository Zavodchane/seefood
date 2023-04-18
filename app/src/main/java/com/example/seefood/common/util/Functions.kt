package com.example.seefood.common.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDateAsString(): String {
   val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.ROOT)
   return sdf.format(Date())
}