package com.example.seefood.screens.catalogs_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seefood.database.objects.Catalog
import com.example.seefood.database.objects.Dish
import com.example.seefood.database.repos.CatalogRepository
import com.example.seefood.database.repos.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CatalogsMenuViewModelAbstract {
   val catalogsListFlow: Flow<List<Catalog>>
   fun addCatalog(catalog: Catalog)
   fun deleteCatalog(catalog: Catalog)
}

@HiltViewModel
class CatalogsMenuViewModel
@Inject constructor(
   private val catalogRepository: CatalogRepository,
   private val dishRepository: DishRepository
) : ViewModel(), CatalogsMenuViewModelAbstract {

   override val catalogsListFlow: Flow<List<Catalog>>
      get() = catalogRepository.getAllCatalogs()

   override fun addCatalog(catalog: Catalog) {
      viewModelScope.launch {
         catalogRepository.upsertCatalog(catalog = catalog)
      }
   }
   override fun deleteCatalog(catalog: Catalog) {
      viewModelScope.launch {
         var catalogsDishesList = listOf<Dish>()
         dishRepository.getDishesByCatalogName(catalogName = catalog.name).collect{ value -> catalogsDishesList = value }

         for (dish in catalogsDishesList) {
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
         catalogRepository.deleteCatalog(catalog = catalog)
      }
   }
}