package com.smh.foodapp.presentation.ui.RecipeDetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.smh.foodapp.domain.model.ExtendedIngredient
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.util.Constants

@Composable
fun IngredientItem(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    ingredient: ExtendedIngredient
) {
    FoodAppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable { },
            backgroundColor = MaterialTheme.colors.surface,
            shape = MaterialTheme.shapes.medium
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                val painter = rememberImagePainter(
                    data = Constants.BASE_IMAGE_URL + ingredient.image,
                    builder = { crossfade(300) }
                )
                Image(
                    painter = painter,
                    contentDescription = "image",
                    alignment = Alignment.Center,
                    modifier = Modifier.size(100.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(200.dp)
                        .weight(2f)
                        .padding(12.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = ingredient.name,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Text(
                            text = ingredient.amount.toString(),
                            style = MaterialTheme.typography.subtitle2,
                            color = MaterialTheme.colors.onBackground
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = ingredient.amount.toString(),
                            style = MaterialTheme.typography.subtitle2,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = ingredient.consistency,
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = ingredient.original,
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}