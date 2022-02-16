package com.smh.foodapp.presentation.ui.RecipeDetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smh.foodapp.R
import com.smh.foodapp.data.database.RecipeDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeDao: RecipeDao
) : ViewModel() {

    private val _snackBarMessage = MutableSharedFlow<String>()
    val snackBarMessage = _snackBarMessage.asSharedFlow()

    val iconId : MutableState<Int> = mutableStateOf(R.drawable.ic_bookmark)

    fun onEvent(event: RecipeDetailEvent) {
        when (event) {
            is RecipeDetailEvent.Save -> {
                viewModelScope.launch {
                    try {
                        _snackBarMessage.emit("Recipe Saved In Favorites")
                        recipeDao.insertFavoriteRecipe(event.favoriteEntity)
                    } catch (e: Exception) {
                        _snackBarMessage.emit(e.message ?: "Couldn't Save Recipe")
                    }
                }
            }
            is RecipeDetailEvent.UnSave -> {
                viewModelScope.launch {
                    try {
                        _snackBarMessage.emit("Recipe Remove From Favorites")
                        recipeDao.deleteFavoriteRecipe(event.favoriteEntity)
                    } catch (e: Exception) {
                        _snackBarMessage.emit(e.message ?: "Couldn't Remove Recipe")
                    }
                }
            }
        }
    }

    fun setFavoriteIcon(id: Int) {
        viewModelScope.launch {
            recipeDao.getFavoriteRecipes().forEach { favoriteEntity ->
                if (favoriteEntity.id == id)
                    iconId.value = R.drawable.ic_fill_bookmark
            }
        }
    }

}


