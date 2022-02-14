package com.smh.foodapp.domain.model

import com.smh.foodapp.domain.model.CuisineType.*

enum class CuisineType(val text: String) {
    SPANISH("Spanish"),
    ITALIAN("Italian"),
    FRENCH("French"),
    BRITISH("British"),
    INDIAN("Indian"),
    KOREAN("Korean"),
    CHINESE("Chinese"),
    AFRICAN("African"),
    AMERICAN("American"),
    MEXICAN("Mexican")
}

fun getAllCuisineType() : List<CuisineType> {
    return listOf(
        SPANISH, ITALIAN, FRENCH, BRITISH, INDIAN, KOREAN, CHINESE, AFRICAN, AMERICAN, MEXICAN
    )
}

fun getCuisineType(value: String) : CuisineType {
    val map = CuisineType.values().associateBy(CuisineType::text)
    return map[value]!!
}
