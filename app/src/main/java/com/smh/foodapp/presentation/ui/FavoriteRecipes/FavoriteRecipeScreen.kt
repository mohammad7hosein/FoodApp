package com.smh.foodapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.RecipeList.RecipeListViewModel


@Composable
fun FavoriteRecipeScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: RecipeListViewModel = hiltViewModel()
) {


    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//            ) {
//                items(recipes) { recipe ->
//                    RecipeItem(
//                        isDarkTheme = isDarkTheme,
//                        image = recipe.image,
//                        title = recipe.title,
//                        summary = recipe.summary,
//                        likes = recipe.aggregateLikes,
//                        minutes = recipe.readyInMinutes,
//                        isVegan = recipe.vegan,
//                        onClick = {
//
//                        }
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                }
//            }

        }
    }
}