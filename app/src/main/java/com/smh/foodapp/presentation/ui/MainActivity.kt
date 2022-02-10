package com.smh.foodapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smh.foodapp.data.datastore.SettingsDataStore
import com.smh.foodapp.domain.model.Screen
import com.smh.foodapp.domain.network.ConnectivityManager
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.FoodJoke.FoodJokeScreen
import com.smh.foodapp.presentation.ui.RecipeDetail.RecipeDetailScreen
import com.smh.foodapp.presentation.ui.RecipeList.RecipeListScreen
import com.smh.foodapp.presentation.ui.component.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme(
                darkTheme = false,
                isNetworkAvailable = true
            ) {
                val bottomBarState = rememberSaveable { mutableStateOf(true) }
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            bottomBarState = bottomBarState
                        )
                    },
                    content = {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Dashboard.route
                        ) {
                            composable(Screen.Dashboard.route) {
                                LaunchedEffect(Unit) { bottomBarState.value = true }
                                RecipeListScreen(
                                    isDarkTheme = settingsDataStore.isDark.value,
                                    isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                                    navController = navController
                                )
                            }
                            composable(Screen.Favorite.route) {
                                LaunchedEffect(Unit) { bottomBarState.value = true }
                                FavoriteRecipeScreen(
                                    isDarkTheme = settingsDataStore.isDark.value,
                                    isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                                )
                            }
                            composable(Screen.FoodJoke.route) {
                                LaunchedEffect(Unit) { bottomBarState.value = true }
                                FoodJokeScreen(
                                    isDarkTheme = settingsDataStore.isDark.value,
                                    isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                                )
                            }
                            composable(Screen.Detail.route) {
                                LaunchedEffect(Unit) { bottomBarState.value = false }
                                RecipeDetailScreen(
                                    isDarkTheme = settingsDataStore.isDark.value,
                                    isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

