package com.smh.foodapp.presentation.ui.RecipeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.NavHostController
import com.smh.foodapp.R
import com.smh.foodapp.domain.model.Result
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.ui.RecipeDetail.component.CollapsableToolbar

@ExperimentalMaterialApi
@ExperimentalMotionApi
@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    navController: NavHostController,
    recipe: Result
) {

    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Column {
            TopBar(navController = navController)
            CollapsableToolbar(
                isDarkTheme = isDarkTheme,
                recipe = recipe
            )
        }
    }
}

@Composable
fun TopBar(
    navController: NavHostController,
) {
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
                // save to favorite
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "save",
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
