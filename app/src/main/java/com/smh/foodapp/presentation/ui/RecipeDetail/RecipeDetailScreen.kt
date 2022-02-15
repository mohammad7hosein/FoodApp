package com.smh.foodapp.presentation.ui.RecipeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.smh.foodapp.R
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.domain.model.Result
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    navController: NavHostController,
    recipe: Result
) {
    val pagerState = rememberPagerState(pageCount = 3)

    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Column {
            TopBar(navController = navController)
            Tabs(pagerState = pagerState)
            TabsContent(
                pagerState = pagerState,
                isDarkTheme = isDarkTheme,
                isNetworkAvailable = isNetworkAvailable,
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
            .padding(12.dp),
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

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {

    val titles = listOf("Overview", "Ingredient", "Instruction")
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onBackground,
        divider = {
            TabRowDefaults.Divider(
                thickness = 0.dp
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = MaterialTheme.colors.onPrimary
            )
        },
        modifier = Modifier.clip(MaterialTheme.shapes.medium)
    ) {
        titles.forEachIndexed { index, _ ->
            Tab(
                selected = pagerState.currentPage == index,
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.surface,
                text = {
                    Text(
                        text = titles[index],
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle2
                    )
                },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                })
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    recipe: Result
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> Overview()
            1 -> Ingredient(
                isDarkTheme = isDarkTheme,
                isNetworkAvailable = isNetworkAvailable,
                ingredients = recipe.extendedIngredients
            )
            2 -> Instruction(url = recipe.sourceUrl)
        }
    }
}

