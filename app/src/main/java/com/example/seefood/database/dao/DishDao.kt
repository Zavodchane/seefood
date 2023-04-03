package com.example.seefood.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.seefood.database.objects.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

   @Upsert
   suspend fun upsertDish(dish: Dish)

   @Delete
   suspend fun deleteDish(dish: Dish)

   @Query("SELECT * FROM dishes WHERE is_favorite = TRUE")
   fun getFavoriteDishes() : Flow<List<Dish>>

   @Query("SELECT * FROM dishes WHERE catalog = :catalogName")
   fun getDishesByCatalogName(catalogName: String) : Flow<List<Dish>>
}