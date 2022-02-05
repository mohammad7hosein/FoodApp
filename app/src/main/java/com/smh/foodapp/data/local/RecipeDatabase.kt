package com.smh.foodapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smh.foodapp.data.local.entity.FavoriteEntity
import com.smh.foodapp.data.local.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

}