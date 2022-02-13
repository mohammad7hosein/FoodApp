package com.smh.foodapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smh.foodapp.util.Constants
import com.smh.foody.models.Recipe

@Entity(tableName = Constants.RECIPES_TABLE)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var recipe: Recipe
)
