package com.example.seefood.screens.dish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seefood.database.objects.Dish
import com.example.seefood.database.repos.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DishViewModelAbstract {
   fun getRelatedDish(dishId : Int) : Flow<Dish>

   fun removeDishFromCatalog(dish: Dish)
}

@HiltViewModel
class DishViewModel
@Inject constructor(
   private val dishRepository: DishRepository
) : ViewModel(), DishViewModelAbstract {
   override fun getRelatedDish(dishId: Int): Flow<Dish> {
      return dishRepository.getDishById(dishId)
   }

   override fun removeDishFromCatalog(dish: Dish) {
      viewModelScope.launch {
         if (dish.isFavorite) {
            dishRepository.upsertDish(
               Dish(
                  name = dish.name,
                  recipe = dish.recipe,
                  imgLocalPath = dish.imgLocalPath,
                  catalog = "",
                  isFavorite = dish.isFavorite,

                  id = dish.id
               )
            )
         }
         else {
            dishRepository.deleteDish(dish)
         }
      }
   }
}