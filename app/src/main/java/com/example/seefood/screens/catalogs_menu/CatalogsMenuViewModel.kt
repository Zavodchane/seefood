package com.example.seefood.screens.catalogs_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seefood.database.objects.Catalog
import com.example.seefood.database.repos.CatalogRepository
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
   private val catalogRepository: CatalogRepository
) : ViewModel(), CatalogsMenuViewModelAbstract {

   init {
      // Для тестов заполняю фейковыми категориями
      viewModelScope.launch {
         catalogRepository.upsertCatalog(
            Catalog(name = "fake_category")
         )
         catalogRepository.upsertCatalog(
            Catalog(name = "sample_catalog_2")
         )
         catalogRepository.upsertCatalog(
            Catalog(name = "sample_catalog_3")
         )
         catalogRepository.upsertCatalog(
            Catalog(name = "sample_catalog_4")
         )
         catalogRepository.upsertCatalog(
            Catalog(name = "sample_catalog_5")
         )
      }
   }

   override val catalogsListFlow: Flow<List<Catalog>>
      get() = catalogRepository.getAllCatalogs()

   override fun addCatalog(catalog: Catalog) {
      viewModelScope.launch {
         catalogRepository.upsertCatalog(catalog = catalog)
      }
   }
   override fun deleteCatalog(catalog: Catalog) {
      viewModelScope.launch {
         catalogRepository.deleteCatalog(catalog = catalog)
      }
   }
}