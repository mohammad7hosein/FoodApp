package com.smh.foodapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.smh.foodapp.data.datastore.SettingsDataStore
import com.smh.foodapp.domain.model.Screen
import com.smh.foodapp.domain.network.ConnectivityManager
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.FoodJoke.FoodJokeScreen
import com.smh.foodapp.presentation.ui.RecipeDetail.RecipeDetailScreen
import com.smh.foodapp.presentation.ui.RecipeList.RecipeListScreen
import com.smh.foodapp.presentation.ui.RecipeList.RecipeListViewModel
import com.smh.foodapp.presentation.ui.component.BottomNavigationBar
import com.smh.foodapp.util.Constants.Companion.RECIPE_KEY
import com.smh.foody.models.Result
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalPagerApi
@ExperimentalComposeUiApi
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

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.showSplash.value
            }
        }
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
                            isDarkTheme = settingsDataStore.isDark.value,
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
                                    onToggleTheme = settingsDataStore::toggleTheme,
                                    navController = navController,
                                    viewModel = viewModel
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
//                            val recipe = navController.previousBackStackEntry?.savedStateHandle?.get<Result>(RECIPE_KEY)
//                            recipe?.let {
//                                composable(Screen.Detail.route) {
//                                    LaunchedEffect(Unit) { bottomBarState.value = false }
//                                    RecipeDetailScreen(
//                                        isDarkTheme = settingsDataStore.isDark.value,
//                                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
//                                        navController = navController,
//                                        recipe = recipe
//                                    )
//                                }
//                            }
                        }
                    }
                )
            }
        }
    }
}

