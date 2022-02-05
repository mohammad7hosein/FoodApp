package com.smh.foodapp.domain.model

import android.os.Parcelable
import com.smh.foodapp.data.local.entity.RecipeEntity
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Recipe(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients: @RawValue List<ExtendedIngredient>,
    val glutenFree: Boolean,
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
) : Parcelable {

    fun toRecipeEntity(): RecipeEntity {
        return RecipeEntity(
            id = id,
            recipe = this
        )
    }

}