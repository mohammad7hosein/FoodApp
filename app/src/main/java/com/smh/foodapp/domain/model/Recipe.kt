package com.smh.foody.models


import com.google.gson.annotations.SerializedName
import com.smh.foodapp.domain.model.Result

data class Recipe(
    @SerializedName("results")
    val results: List<Result>,
)