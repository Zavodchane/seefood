package com.example.seefood.database.repos

import com.example.seefood.database.dao.DishDao
import com.example.seefood.database.objects.Dish
import kotlinx.coroutines.flow.Flow

class DishRepository(
   private val dishDao: DishDao
) {
   suspend fun upsertDish(dish: Dish) = dishDao.upsertDish(dish = dish)
   suspend fun deleteDish(dish: Dish) = dishDao.deleteDish(dish = dish)
   fun getFavoriteDishes() : Flow<List<Dish>> = dishDao.getFavoriteDishes()
   fun getDishesByCategory(category: String) : Flow<List<Dish>> = dishDao.getDishesByCategory(category = category)
}