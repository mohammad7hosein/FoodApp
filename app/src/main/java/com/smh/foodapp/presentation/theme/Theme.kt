package com.smh.foodapp.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.smh.foodapp.presentation.ui.component.ConnectivityMonitor
import com.smh.foodapp.presentation.ui.component.GenericDialogInfo
import com.smh.foodapp.presentation.ui.component.ProcessDialogQueue
import java.util.*

private val DarkColorPalette = darkColors(
    primary = Primary,
    background = Dark,
    onBackground = White,
    surface = DarkGray,
    onSurface = LightGray,
    onPrimary = Primary,
)

private val LightColorPalette = lightColors(
    primary = Accent,
    background = LightGray,
    onBackground = Dark,
    surface = White,
    onSurface = Gray,
    onPrimary = Accent,
)

@Composable
fun FoodAppTheme(
    darkTheme: Boolean,
    isNetworkAvailable: Boolean,
//    displayProgressBar: Boolean,
//    scaffoldState: ScaffoldState,
    dialogQueue: Queue<GenericDialogInfo>? = null,
    content: @Composable () -> Unit,
) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = if (darkTheme) Dark else LightGray
        )
    }

    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Column {
                ConnectivityMonitor(isNetworkAvailable = isNetworkAvailable)
                content()
            }
//            CircularIndeterminateProgressBar(isDisplayed = displayProgressBar, 0.3f)
//            DefaultSnackbar(
//                snackbarHostState = scaffoldState.snackbarHostState,
//                onDismiss = {
//                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
//                },
//                modifier = Modifier.align(Alignment.BottomCenter)
//            )
            ProcessDialogQueue(
                dialogQueue = dialogQueue,
            )
        }
    }
}