package com.example.seefood.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seefood.database.objects.Dish
import com.example.seefood.database.repos.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CatalogViewModelAbstract {
   fun getCatalogsDishesListFlow(catalogName: String) : Flow<List<Dish>>
   fun removeDishFromCatalog(dish: Dish)
//   fun addDishToCatalog(dish: Dish) TODO: Может потом доделать чтобы добавлять из избранного в каталоги
}

@HiltViewModel
class CatalogViewModel
@Inject constructor(
   private val dishRepository: DishRepository
) : ViewModel(), CatalogViewModelAbstract {

   override fun getCatalogsDishesListFlow(catalogName: String): Flow<List<Dish>> {
      return dishRepository.getDishesByCatalogName(catalogName = catalogName)
   }

   override fun removeDishFromCatalog(dish: Dish) {
      viewModelScope.launch {
         if (!dish.isFavorite){
            viewModelScope.launch { dishRepository.deleteDish(dish) }
         }
         else {
            viewModelScope.launch {
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
         }
      }
   }
}