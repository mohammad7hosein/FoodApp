package com.smh.foodapp.presentation.ui.FavoriteRecipes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smh.foodapp.data.database.RecipeDao
import com.smh.foodapp.data.database.entity.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipeViewModel @Inject constructor(
    private val recipeDao: RecipeDao
) : ViewModel() {

    val recipes: MutableState<List<FavoriteEntity>> = mutableStateOf(ArrayList())

    init {
        showRecipes()
    }

    fun showRecipes() {
        viewModelScope.launch {
            recipes.value = recipeDao.getFavoriteRecipes()
        }
    }

}