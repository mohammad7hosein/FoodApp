package com.smh.foodapp.presentation.ui.RecipeDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.smh.foodapp.presentation.theme.FoodAppTheme

@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
) {
    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Detail", fontSize = 48.sp)
        }
    }
}