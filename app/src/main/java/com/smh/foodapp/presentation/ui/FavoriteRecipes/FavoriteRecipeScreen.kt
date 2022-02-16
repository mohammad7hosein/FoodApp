package com.smh.foodapp.presentation.ui

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.smh.foodapp.domain.model.Screen
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.FavoriteRecipes.FavoriteRecipeViewModel
import com.smh.foodapp.presentation.ui.component.RecipeItem

@Composable
fun FavoriteRecipeScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    navController: NavHostController,
    viewModel: FavoriteRecipeViewModel = hiltViewModel()
) {

    SideEffect {
        viewModel.showRecipes()
    }
    val recipes = viewModel.recipes.value

    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (recipes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Favorites Is Empty...",
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            else {
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 48.dp, top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    items(recipes) { recipe ->
                        RecipeItem(
                            isDarkTheme = isDarkTheme,
                            image = recipe.result.image,
                            title = recipe.result.title,
                            summary = recipe.result.summary,
                            likes = recipe.result.aggregateLikes,
                            minutes = recipe.result.readyInMinutes,
                            isVegan = recipe.result.vegan,
                            onClick = {
                                val json = Uri.encode(Gson().toJson(recipe.result))
                                navController.navigate("${Screen.Detail.route}/$json")
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}