package com.smh.foodapp.domain.model

import com.smh.foodapp.domain.model.DietType.*

enum class DietType(val value: String) {
    GLUTEN_FREE("Gluten Free"),
    KETOGENIC("Ketogenic"),
    VEGETARIAN("Vegetarian"),
    LACTO_VEG("Lacto Veg"),
    OVO_VEG("Ovo Veg"),
    VEGAN("Vegan"),
    PESCETARIAN("Pescetarian"),
    PALEO("Paleo"),
    PRIMAL("Primal"),
    LOW_FODMAP("Low Fodmap")
}

fun getALLDietType() : List<DietType> {
    return listOf(
        GLUTEN_FREE, KETOGENIC, VEGETARIAN, LACTO_VEG, OVO_VEG, VEGAN, PESCETARIAN, PALEO, PRIMAL, LOW_FODMAP
    )
}

fun getDietType(value: String) : DietType? {
    val map = values().associateBy(DietType::value)
    return map[value]
}

