package com.smh.foodapp.domain.model

data class FilterType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int,
    val selectedCuisineType: String,
    val selectedCuisineTypeId: Int
)
