package com.smh.foodapp.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.smh.foodapp.domain.model.Screen
import com.smh.foodapp.presentation.theme.*

@Composable
fun BottomNavigationBar(
    isDarkTheme: Boolean,
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    val items = listOf(
        Screen.Favorite,
        Screen.Dashboard,
        Screen.FoodJoke
    )
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
                backgroundColor = if (isDarkTheme) DarkGray else White,
                elevation = 45.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    val selected = item.route == currentRoute
                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route)
                            {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selectedContentColor = if (isDarkTheme) Primary else Accent,
                        unselectedContentColor = if (isDarkTheme) LightGray else Gray,
                        icon = {
                            Icon(
                                painter = painterResource(id = if (selected) item.selectedIconId else item.unSelectedIconId),
                                contentDescription = item.name
                            )
                        }
                    )
                }
            }
        }
    )
}
