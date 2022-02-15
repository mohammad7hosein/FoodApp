package com.smh.foodapp.presentation.ui.RecipeList

import androidx.compose.ui.focus.FocusState

sealed class RecipeListEvent {
    data class EnteredText(val text: String) : RecipeListEvent()
    data class ChangeTextFocus(val focusState: FocusState) : RecipeListEvent()
    object Filter : RecipeListEvent()
    data class Search(val query: String) : RecipeListEvent()
}