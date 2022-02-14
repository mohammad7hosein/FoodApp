package com.smh.foodapp.data.database

import androidx.room.*
import com.smh.foodapp.data.database.entity.FavoriteEntity

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_recipes_table")
    suspend fun getFavoriteRecipes(): List<FavoriteEntity>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

}