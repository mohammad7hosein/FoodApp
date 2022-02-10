package com.smh.foodapp.domain.model

import com.smh.foodapp.domain.model.MealType.*


enum class MealType(val value: String) {
    MAIN_COURSE("main course"),
    DESSERT("dessert"),
    APPETIZER("appetizer"),
    SALAD("salad"),
    BREAD("bread"),
    BREAKFAST("breakfast"),
    SOUP("soup"),
    DRINK("drink"),
    BEVERAGE("beverage"),
    SAUCE("sauce")
}

fun getAllMealType() : List<MealType> {
    return listOf(
        MAIN_COURSE, DESSERT, APPETIZER, SALAD, BREAD, BREAKFAST, SOUP, DRINK, BEVERAGE, SAUCE
    )
}

fun getMealType(value: String) : MealType? {
    val map = values().associateBy(MealType::value)
    return map[value]
}

enum class DietType(value: String) {

}

enum class CuisineType(value: String) {

}