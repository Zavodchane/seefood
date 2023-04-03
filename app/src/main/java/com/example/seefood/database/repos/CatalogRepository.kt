package com.example.seefood.database.repos

import com.example.seefood.database.dao.CatalogDao
import com.example.seefood.database.objects.Catalog

class CatalogRepository(
   private val catalogDao: CatalogDao
) {
   suspend fun upsertCatalog(catalog: Catalog) = catalogDao.upsertCatalog(catalog = catalog)
   suspend fun deleteCatalog(catalog: Catalog) = catalogDao.deleteCatalog(catalog = catalog)
   fun getAllCatalogs()                        = catalogDao.getAllCatalogs()
}