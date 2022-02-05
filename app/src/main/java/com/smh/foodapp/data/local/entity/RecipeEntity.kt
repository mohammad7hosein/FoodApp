package com.smh.foodapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smh.foodapp.domain.model.Recipe
import com.smh.foodapp.util.Constants

@Entity(tableName = Constants.RECIPES_TABLE)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var recipe: Recipe
)
