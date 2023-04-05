package com.example.seefood.database.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalogs")
data class Catalog (
   @PrimaryKey
   val name               : String,
   val creationDate       : String,
   val thumbnailLocalPath : String
)
