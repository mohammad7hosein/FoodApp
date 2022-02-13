package com.smh.foodapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.RecipeList.RecipeListViewModel
import com.smh.foodapp.presentation.ui.component.RecipeItem

@Composable
fun FavoriteRecipeScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                items(state.recipes) { recipe ->
                    RecipeItem(
                        isDarkTheme = isDarkTheme,
                        image = recipe.image,
                        title = recipe.title,
                        summary = recipe.summary,
                        likes = recipe.aggregateLikes,
                        minutes = recipe.readyInMinutes,
                        isVegan = recipe.vegan,
                        onClick = {

                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
    }
}