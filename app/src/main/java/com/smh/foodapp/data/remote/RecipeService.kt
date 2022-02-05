package com.smh.foodapp.data.remote

import com.smh.foodapp.domain.model.FoodJoke
import com.smh.foodapp.domain.model.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RecipeService {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queries: Map<String, String>) : Response<Recipe>

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(@QueryMap searchQuery: Map<String, String>) : Response<Recipe>

    @GET("/food/jokes/random")
    suspend fun getFoodJoke(@Query("apiKey") apiKey: String) : Response<FoodJoke>

}