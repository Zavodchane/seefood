package com.example.seefood.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.seefood.database.dao.DishDao
import com.example.seefood.database.objects.Dish

@Database(
   entities = [Dish::class],
   version  = 1
)
abstract class SeeFoodDatabase : RoomDatabase() {

   abstract val dishDao: DishDao

   companion object {
      private var INSTANCE: SeeFoodDatabase? = null

      fun getInstance(context: Context) : SeeFoodDatabase {
         if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, SeeFoodDatabase::class.java, "dishdata.db")
               .fallbackToDestructiveMigration()
               .build()
         }
         return INSTANCE as SeeFoodDatabase
      }
   }
}