package com.example.seefood.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.seefood.database.objects.Dish
import kotlinx.coroutines.flow.Flow
import java.io.File

@Dao
interface DishDao {

   @Upsert
   suspend fun upsertDish(dish: Dish)

   @Delete // Так же как в каталогах думаю будет, пока не проверял, но заранее меняю на новую функцию удаления
   suspend fun deleteDish(dish: Dish) {
      val file = File(dish.imgLocalPath)
      if (file.exists()) {
         file.delete()
      }
   }

   @Query("DELETE FROM dishes WHERE id = :id")
   suspend fun deleteDishById(id: Int)

   @Query("SELECT * FROM dishes WHERE catalog = :catalogName")
   suspend fun getDishesByCatalogNameList(catalogName: String) : List<Dish>

   @Query("SELECT * FROM dishes WHERE id = :id")
   fun getDishById(id: Int) : Flow<Dish?>

   @Query("SELECT * FROM dishes WHERE is_favorite = TRUE")
   fun getFavoriteDishes() : Flow<List<Dish>>

   @Query("SELECT * FROM dishes WHERE catalog = :catalogName")
   fun getDishesByCatalogName(catalogName: String) : Flow<List<Dish>>
}