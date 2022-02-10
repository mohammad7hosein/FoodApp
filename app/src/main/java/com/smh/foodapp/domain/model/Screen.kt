package com.smh.foodapp.domain.model

import androidx.annotation.DrawableRes
import com.smh.foodapp.R

sealed class Screen(
    val name: String,
    val route: String,
    @DrawableRes val unSelectedIconId: Int = -1,
    @DrawableRes val selectedIconId: Int = -1
) {
    object Dashboard : Screen(
        name = "Foody",
        route = "recipes",
        unSelectedIconId = R.drawable.ic_dashboard,
        selectedIconId = R.drawable.ic_fill_dashboard
    )

    object Favorite : Screen(
        name = "Favorites",
        route = "favorite",
        unSelectedIconId = R.drawable.ic_bookmark,
        selectedIconId = R.drawable.ic_fill_bookmark
    )

    object FoodJoke : Screen(
        name = "FoodJoke",
        route = "food_joke",
        unSelectedIconId = R.drawable.ic_joker,
        selectedIconId = R.drawable.ic_fill_joker
    )

    object Detail : Screen(
        name = "Detail",
        route = "detail"
    )

}
