package com.smh.foodapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smh.foodapp.util.Constants
import com.smh.foodapp.domain.model.Result

@Entity(tableName = Constants.FAVORITE_RECIPES_TABLE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)