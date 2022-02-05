package com.smh.foodapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smh.foodapp.domain.model.Recipe

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
}
