package com.example.seefood.screens.classification_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seefood.database.objects.Dish
import com.example.seefood.database.repos.CatalogRepository
import com.example.seefood.database.repos.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ClassificationResultViewModelAbstract {
   fun getRelatedDish(dishId : Int) : Flow<Dish?>
   fun changeDishFavoritesState(dish : Dish, favoriteState: Boolean)
   fun changeDishCatalog(dish: Dish, catalogName: String)
}

@HiltViewModel
class ClassificationResultViewModel
@Inject constructor(
   private val dishRepository: DishRepository,
   private val catalogRepository: CatalogRepository
) : ViewModel(), ClassificationResultViewModelAbstract {
   override fun getRelatedDish(dishId: Int): Flow<Dish?> {
      return dishRepository.getDishById(dishId)
   }

   override fun changeDishFavoritesState(dish: Dish, favoriteState : Boolean) {
      viewModelScope.launch {
         dishRepository.upsertDish(
            Dish(
               name = dish.name,
               recipe = dish.recipe,
               imgLocalPath = dish.imgLocalPath,
               isFavorite = favoriteState,
               id = dish.id,
            )
         )
      }
   }

   override fun changeDishCatalog(dish: Dish, catalogName: String) {
      TODO("Not yet implemented")
   }
}