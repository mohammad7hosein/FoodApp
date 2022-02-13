package com.smh.foodapp.presentation.ui.FoodJoke

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.smh.foodapp.presentation.theme.FoodAppTheme
import kotlinx.coroutines.flow.collect

@Composable
fun FoodJokeScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: FoodJokeViewModel = hiltViewModel()
) {

    val dialogQueue = viewModel.dialogQueue
    val state = viewModel.state.value

    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        dialogQueue = dialogQueue.queue.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "FoodJoke",
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colors.surface
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.text,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}