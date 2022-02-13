package com.smh.foodapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.smh.foodapp.R

val viga = FontFamily(
    listOf(
        Font(R.font.viga)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    h3 = TextStyle(
        fontFamily = viga,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp
    ),
    h5 = TextStyle(
        fontFamily = viga,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    h6 = TextStyle(
        fontFamily = viga,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = viga,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = viga,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = viga,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = viga,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)