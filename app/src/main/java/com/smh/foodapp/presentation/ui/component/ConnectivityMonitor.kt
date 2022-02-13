package com.smh.foodapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smh.foodapp.presentation.theme.Red

@Composable
fun ConnectivityMonitor(
    isNetworkAvailable: Boolean,
) {
    if (!isNetworkAvailable) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Red)) {
            Text(
                "No network connection",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}