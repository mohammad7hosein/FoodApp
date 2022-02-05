package com.smh.foodapp.data.local

import androidx.room.*
import com.smh.foodapp.data.local.entity.FavoriteEntity
import com.smh.foodapp.data.local.entity.RecipeEntity
import com.smh.foodapp.util.Constants.Companion.RECIPE_PAGINATION_PAGE_SIZE

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipes(favoritesEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_recipes_table")
    fun getFavoriteRecipes(): List<FavoriteEntity>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipeEntity)

    @Query("SELECT * FROM recipes_table WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeEntity?

    /**
     * Retrieve recipes for a particular page.
     * Ex: page = 2 retrieves recipes from 30-60.
     * Ex: page = 3 retrieves recipes from 60-90
     */

    @Query("SELECT * FROM recipes_table LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)")
    suspend fun getAllRecipes(
        page: Int,
        pageSize: Int = RECIPE_PAGINATION_PAGE_SIZE
    ): List<RecipeEntity>

    /**
     * Restore Recipes after process death
     */
    @Query("SELECT * FROM recipes_table LIMIT (:page * :pageSize)")
    suspend fun restoreAllRecipes(
        page: Int,
        pageSize: Int = RECIPE_PAGINATION_PAGE_SIZE
    ): List<RecipeEntity>

}