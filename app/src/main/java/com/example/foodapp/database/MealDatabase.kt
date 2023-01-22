package com.example.foodapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapp.model.Meal


@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class MealDatabase: RoomDatabase() {
    abstract val dao: MealDao

    companion object {
        @Volatile
        private var instance: MealDatabase? = null

        operator fun invoke(context: Context): MealDatabase = instance ?: synchronized(Any()) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): MealDatabase {
            return Room.databaseBuilder(
                context,
                MealDatabase::class.java,
                "Meal.db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}