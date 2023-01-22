package com.example.foodapp.database

import androidx.room.*
import com.example.foodapp.model.Meal

@Dao
interface MealDao {
    @Query("SELECT * FROM Meal ORDER BY id DESC")
    fun getAllMeals(): List<Meal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMeal(club: Meal)
}