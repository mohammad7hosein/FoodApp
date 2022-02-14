package com.smh.foodapp.util

class Constants {

    companion object {

        const val TAG = "FoodDebug"

        const val BASE_URL = "https://api.spoonacular.com"
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
        const val API_KEY = "d1a4e14e097a4380b6e3492125f3e473"

        const val RECIPE_RESULT_KEY = "recipeBundle"

        // Api Query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_CUISINE = "cuisine"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        // Room Database
        const val DATABASE_NAME = "recipes_db"
        const val FAVORITE_RECIPES_TABLE = "favorite_recipes_table"

        // DataStore
        const val PREFERENCES_NAME = "foody_preferences"
        const val DEFAULT_RECIPES_NUMBER = "50"
        const val PREFERENCES_DARK_THEME = "dark_theme"

        // RecipeListViewModel
        const val STATE_KEY_SELECTED_Meal = "selected_meal"
        const val STATE_KEY_SELECTED_Diet = "selected_diet"
        const val STATE_KEY_SELECTED_Cuisine = "selected_cuisine"
    }

}