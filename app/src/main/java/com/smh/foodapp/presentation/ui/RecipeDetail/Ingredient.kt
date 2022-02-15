package com.smh.foodapp.presentation.ui.RecipeDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smh.foodapp.domain.model.ExtendedIngredient
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.RecipeDetail.component.IngredientItem

@Composable
fun Ingredient(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    ingredients: List<ExtendedIngredient>
) {
    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                items(ingredients) { ingredient ->
                    IngredientItem(
                        isDarkTheme = isDarkTheme,
                        isNetworkAvailable = isNetworkAvailable,
                        ingredient = ingredient
                    )
                }
            }
        }
    }
}