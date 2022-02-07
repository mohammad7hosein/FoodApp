package com.smh.foodapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smh.foodapp.R
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.FoodJoke.FoodJokeScreen
import com.smh.foodapp.presentation.ui.RecipeList.RecipeListScreen
import com.smh.foodapp.presentation.ui.component.BottomNavItem
import com.smh.foodapp.presentation.ui.component.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Favorite",
                                        route = "favorite",
                                        unSelectedIconId = R.drawable.ic_bookmark,
                                        selectedIconId = R.drawable.ic_fill_bookmark
                                    ),
                                    BottomNavItem(
                                        name = "Recipes",
                                        route = "recipes",
                                        unSelectedIconId = R.drawable.ic_dashboard,
                                        selectedIconId = R.drawable.ic_fill_dashboard
                                    ),
                                    BottomNavItem(
                                        name = "FoodJoke",
                                        route = "food_joke",
                                        unSelectedIconId = R.drawable.ic_joker,
                                        selectedIconId = R.drawable.ic_fill_joker
                                    ),
                                ),
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route) {
                                        popUpTo(navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                                }
                            )
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "recipes") {
        composable("recipes") {
            RecipeListScreen()
        }
        composable("favorite") {
            FavoriteRecipeScreen()
        }
        composable("food_joke") {
            FoodJokeScreen()
        }
    }

}
