package com.smh.foodapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smh.foody.models.Recipe
import com.smh.foody.models.Result

class RecipeTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun recipeToString(recipe: Recipe): String {
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun stringToRecipe(data: String): Recipe {
        val listType = object : TypeToken<Recipe>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }

}
