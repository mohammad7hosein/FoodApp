package com.smh.foodapp.presentation.ui.RecipeList

import com.smh.foodapp.domain.model.FilterType
import com.smh.foodapp.util.Constants.Companion.DEFAULT_CUISINE_TYPE
import com.smh.foodapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.smh.foodapp.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.smh.foody.models.Result

data class RecipeListState(
    val recipes: List<Result> = emptyList(),
    val filter: FilterType = FilterType(
        selectedMealType = DEFAULT_MEAL_TYPE,
        selectedDietType = DEFAULT_DIET_TYPE,
        selectedCuisineType = DEFAULT_CUISINE_TYPE,
    ),
    val isLoading: Boolean = false
)
