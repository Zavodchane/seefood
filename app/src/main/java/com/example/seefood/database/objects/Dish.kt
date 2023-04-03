package com.example.seefood.database.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class Dish (
   @ColumnInfo(name = "name")                        val name         : String,
   @ColumnInfo(name = "recipe")                      val recipe       : String,
   @ColumnInfo(name = "img_local_path")              val imgLocalPath : String,
   @ColumnInfo(name = "category", defaultValue = "") val category     : String,
   @ColumnInfo(name = "is_favorite")                 val isFavorite   : Boolean,

   @PrimaryKey (autoGenerate = true)
   val id           : Int
)