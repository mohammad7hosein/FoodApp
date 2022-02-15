package com.smh.foodapp.presentation.ui.RecipeDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.smh.foodapp.domain.model.Result
import com.smh.foodapp.presentation.theme.Gray
import com.smh.foodapp.presentation.theme.Green
import com.smh.foodapp.presentation.theme.LightGray
import org.jsoup.Jsoup

@Composable
internal fun ScrollableContent(
    isDarkTheme: Boolean,
    recipe: Result
) {
    Column(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "checkmark",
                        tint = if (recipe.vegetarian) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Vegetarian",
                        style = MaterialTheme.typography.subtitle2,
                        color =
                        if (recipe.vegetarian) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "checkmark",
                        tint = if (recipe.vegan) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Vegan",
                        style = MaterialTheme.typography.subtitle2,
                        color =
                        if (recipe.vegan) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "checkmark",
                        tint = if (recipe.glutenFree) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Gluten Free",
                        style = MaterialTheme.typography.subtitle2,
                        color =
                        if (recipe.glutenFree) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "checkmark",
                        tint = if (recipe.dairyFree) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "dairy Free",
                        style = MaterialTheme.typography.subtitle2,
                        color =
                        if (recipe.dairyFree) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "checkmark",
                        tint = if (recipe.veryHealthy) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Healthy",
                        style = MaterialTheme.typography.subtitle2,
                        color =
                        if (recipe.veryHealthy) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "checkmark",
                        tint = if (recipe.cheap) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Cheap",
                        style = MaterialTheme.typography.subtitle2,
                        color =
                        if (recipe.cheap) Green
                        else {
                            if (isDarkTheme) LightGray else Gray
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = Jsoup.parse(recipe.summary).text(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,

        )
    }
}

