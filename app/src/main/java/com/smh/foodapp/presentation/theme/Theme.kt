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
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.smh.foodapp.presentation.ui.component.ConnectivityMonitor
import com.smh.foodapp.presentation.ui.component.GenericDialogInfo
import com.smh.foodapp.presentation.ui.component.ProcessDialogQueue
import java.util.*

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Dark,

    )

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
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
                .background(color = if (!darkTheme) LightGray else Color.Black)
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