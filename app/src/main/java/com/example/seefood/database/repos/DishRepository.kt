package com.example.seefood.database.repos

import com.example.seefood.database.dao.DishDao
import com.example.seefood.database.objects.Dish
import kotlinx.coroutines.flow.Flow

class DishRepository(
   private val dishDao: DishDao
) {
   suspend fun upsertDish(dish: Dish) = dishDao.upsertDish(dish = dish)
   suspend fun deleteDish(dish: Dish) = dishDao.deleteDish(dish = dish)
   fun getDishById(id: Int)   = dishDao.getDishById(id = id)
   fun getFavoriteDishes() : Flow<List<Dish>> = dishDao.getFavoriteDishes()
   fun getDishesByCatalogName (catalogName: String) : Flow<List<Dish>> = dishDao.getDishesByCatalogName(catalogName = catalogName)
}