package com.smh.foodapp.presentation.ui.RecipeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.smh.foodapp.R
import com.smh.foodapp.data.database.entity.FavoriteEntity
import com.smh.foodapp.domain.model.Result
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.RecipeDetail.component.CollapsableToolbar
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@ExperimentalMotionApi
@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    navController: NavHostController,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    recipe: Result
) {
    val scaffoldState = rememberScaffoldState()

    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Scaffold(scaffoldState = scaffoldState) {
            Column {
                TopBar(
                    navController = navController,
                    viewModel = viewModel,
                    scaffoldState,
                    recipe = recipe
                )
                CollapsableToolbar(
                    isDarkTheme = isDarkTheme,
                    recipe = recipe
                )
            }

        }
    }
}

@Composable
fun TopBar(
    navController: NavHostController,
    viewModel: RecipeDetailViewModel,
    scaffoldState: ScaffoldState,
    recipe: Result
) {

    viewModel.setFavoriteIcon(recipe.id)
    val iconId = remember {
        viewModel.iconId
    }

    LaunchedEffect(key1 = true) {
        viewModel.snackBarMessage.collectLatest { message ->
            scaffoldState.snackbarHostState.showSnackbar(message,null, SnackbarDuration.Long)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier.size(36.dp)
            )
        }
        IconButton(
            onClick = {
                if (iconId.value == R.drawable.ic_bookmark) {
                    viewModel.onEvent(RecipeDetailEvent.Save(FavoriteEntity(recipe.id, recipe)))
                    iconId.value = R.drawable.ic_fill_bookmark
                } else {
                    viewModel.onEvent(
                        RecipeDetailEvent.UnSave(
                            FavoriteEntity(
                                recipe.id,
                                recipe
                            )
                        )
                    )
                    iconId.value = R.drawable.ic_bookmark
                }
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = iconId.value),
                contentDescription = "save",
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
