package com.smh.foodapp.presentation.ui.RecipeDetail

import com.smh.foodapp.data.database.entity.FavoriteEntity

sealed class RecipeDetailEvent(favoriteEntity: FavoriteEntity) {
    data class Save(val favoriteEntity: FavoriteEntity) : RecipeDetailEvent(favoriteEntity)
    data class UnSave(val favoriteEntity: FavoriteEntity): RecipeDetailEvent(favoriteEntity)
}
