package com.smh.foodapp.presentation.ui.RecipeList

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.smh.foodapp.R
import com.smh.foodapp.domain.model.Screen
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.theme.viga
import com.smh.foodapp.presentation.ui.component.AnimatedShimmer
import com.smh.foodapp.presentation.ui.component.FilterDialog
import com.smh.foodapp.presentation.ui.component.RecipeItem

@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    onToggleTheme: () -> Unit,
    navController: NavHostController,
    viewModel: RecipeListViewModel
) {
    val searchText = viewModel.searchText.value
    val recipes = viewModel.recipes.value
    val isLoading = viewModel.isLoading.value
    val dialogQueue = viewModel.dialogQueue
    val keyboardController = LocalSoftwareKeyboardController.current

    val selectedMealType = viewModel.selectedMealType.value
    val selectedDietType = viewModel.selectedDietType.value
    val selectedCuisineType = viewModel.selectedCuisineType.value

    val isDialogOpen = remember {
        mutableStateOf(false)
    }

    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        dialogQueue = dialogQueue.queue.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Foody",
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
                IconButton(
                    onClick = { onToggleTheme() },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        painter = painterResource(id = if (isDarkTheme) R.drawable.ic_sun else R.drawable.ic_night),
                        contentDescription = "day_night",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(45.dp)
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colors.surface)
                        .padding(8.dp)
                ) {
                    IconButton(onClick = {
                        viewModel.onEvent(RecipeListEvent.Search(searchText.text))
                        keyboardController?.hide()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                    BasicTextField(
                        value = searchText.text,
                        onValueChange = {
                            viewModel.onEvent(RecipeListEvent.EnteredText(it))
                        },
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.onSurface,
                            fontFamily = viga,
                            fontSize = 14.sp
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.onEvent(RecipeListEvent.Search(searchText.text))
                                keyboardController?.hide()
                            }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                viewModel.onEvent(RecipeListEvent.ChangeTextFocus(it))
                            }
                    )
                    if (searchText.isHintVisible)
                        Text(
                            text = searchText.hint,
                            style = TextStyle(
                                color = MaterialTheme.colors.onSurface,
                                fontFamily = viga,
                                fontSize = 14.sp
                            )
                        )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(12.dp)
                        .height(45.dp)
                        .width(45.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colors.surface)
                        .clickable { isDialogOpen.value = true }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "filter",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
            FilterDialog(
                selectedMealType = selectedMealType,
                selectedDietType = selectedDietType,
                selectedCuisineType = selectedCuisineType,
                onSelectedMealTypeChanged = viewModel::onSelectedMealTypeChanged,
                onSelectedDietTypeChanged = viewModel::onSelectedDietTypeChanged,
                onSelectedCuisineTypeChanged = viewModel::onSelectedCuisineTypeChanged,
                isDialogOpen = isDialogOpen,
                onFilterTypeChanged = {
                    viewModel.onEvent(
                        RecipeListEvent.Filter
                    )
                }
            )
            if (isLoading) {
                Column(modifier = Modifier.fillMaxSize()) {
                    repeat(4) {
                        AnimatedShimmer()
                    }
                }
            } else if (recipes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier.padding(4.dp),
                        onClick = { viewModel.onEvent(RecipeListEvent.Filter) }) {
                        Text(
                            text = "Try Again",
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 48.dp, top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    items(recipes) { recipe ->
                        RecipeItem(
                            isDarkTheme = isDarkTheme,
                            image = recipe.image,
                            title = recipe.title,
                            summary = recipe.summary,
                            likes = recipe.aggregateLikes,
                            minutes = recipe.readyInMinutes,
                            isVegan = recipe.vegan,
                            onClick = {
                                val json = Uri.encode(Gson().toJson(recipe))
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

