package com.example.seefood.screens.dish

import androidx.lifecycle.ViewModel
import com.example.seefood.database.objects.Dish
import com.example.seefood.database.repos.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DishViewModelAbstract {
   fun getRelatedDish(dishId : Int) : Flow<Dish>
}

@HiltViewModel
class DishViewModel
@Inject constructor(
   private val dishRepository: DishRepository
) : ViewModel(), DishViewModelAbstract {
   override fun getRelatedDish(dishId: Int): Flow<Dish> {
      return dishRepository.getDishById(dishId)
   }
}