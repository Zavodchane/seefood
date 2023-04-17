package com.example.seefood.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.seefood.database.objects.Catalog
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {

   @Upsert
   suspend fun upsertCatalog(catalog: Catalog)

   @Delete // Не работает если передавать копию объекта переделал в функцию ниже
   suspend fun deleteCatalog(catalog: Catalog)

   @Query("DELETE FROM catalogs WHERE name = :name")
   suspend fun deleteCatalogByName(name: String)

   @Query("SELECT * FROM catalogs")
   fun getAllCatalogs() : Flow<List<Catalog>>
}