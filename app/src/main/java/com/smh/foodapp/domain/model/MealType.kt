package com.smh.foodapp.domain.model

import com.smh.foodapp.domain.model.MealType.*

enum class MealType(val text: String) {
    MAIN_COURSE("Main Course"),
    DESSERT("Dessert"),
    APPETIZER("Appetizer"),
    SALAD("Salad"),
    BREAD("Bread"),
    BREAKFAST("Breakfast"),
    SOUP("Soup"),
    DRINK("Drink"),
    BEVERAGE("Beverage"),
    SAUCE("Sauce")
}

fun getAllMealType() : List<MealType> {
    return listOf(
        MAIN_COURSE, DESSERT, APPETIZER, SALAD, BREAD, BREAKFAST, SOUP, DRINK, BEVERAGE, SAUCE
    )
}

fun getMealType(value: String) : MealType {
    val map = values().associateBy(MealType::text)
    return map[value]!!
}

