package com.smh.foodapp.presentation.ui.RecipeList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.smh.foodapp.R
import com.smh.foodapp.domain.model.Screen
import com.smh.foodapp.presentation.theme.DarkGray
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.theme.viga

@Composable
fun RecipeListScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    navController: NavHostController,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        val searchText = viewModel.searchText.value
        val state = viewModel.state.value
        val dialogQueue = viewModel.dialogQueue

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back",
                        modifier = Modifier.size(36.dp)
                    )
                }
                Text(
                    text = "Foody",
                    style = MaterialTheme.typography.h3,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
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
                        .background(color = Color.White)
                        .padding(8.dp)
                ) {
                    IconButton(onClick = {
                        viewModel.onEvent(RecipeListEvent.Search(searchText.text))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search",
                            tint = DarkGray
                        )
                    }
                    BasicTextField(
                        value = searchText.text,
                        onValueChange = {
                            viewModel.onEvent(RecipeListEvent.EnteredText(it))
                        },
                        textStyle = TextStyle(
                            color = DarkGray,
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
                                color = DarkGray,
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
                        .background(color = Color.White)
                        .clickable {
                            navController.navigate(Screen.Detail.route)
                        }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "filter",
                        tint = DarkGray
                    )
                }
            }
        }
    }
}

