package com.smh.foodapp.presentation.ui.RecipeList

import androidx.compose.ui.focus.FocusState
import com.smh.foodapp.domain.model.FilterType

sealed class RecipeListEvent {
    data class EnteredText(val text: String) : RecipeListEvent()
    data class ChangeTextFocus(val focusState: FocusState) : RecipeListEvent()
    data class Filter(val filterType: FilterType) : RecipeListEvent()
    data class Search(val query: String) : RecipeListEvent()
}