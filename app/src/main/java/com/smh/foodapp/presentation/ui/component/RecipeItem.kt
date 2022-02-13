package com.smh.foodapp.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.smh.foodapp.R
import com.smh.foodapp.presentation.theme.*
import org.jsoup.Jsoup

@Composable
fun RecipeItem(
    isDarkTheme: Boolean,
    image: String,
    title: String,
    summary: String,
    likes: Int,
    minutes: Int,
    isVegan: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            val painter = rememberImagePainter(
                data = image,
                builder = {
                    crossfade(500)
                }
            )
            Image(
                painter = painter,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .size(200.dp)
                    .weight(1f)
                    .background(color = if (isDarkTheme) DarkGray else White)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.h5,
                    color = if (isDarkTheme) LightGray else Black
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = Jsoup.parse(summary).text(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = MaterialTheme.typography.subtitle2,
                    color = if (isDarkTheme) LightGray else Black
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = "heart",
                            tint = Red
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = likes.toString(),
                            style = MaterialTheme.typography.subtitle2,
                            color = Red
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "clock",
                            tint = Yellow
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = minutes.toString(),
                            style = MaterialTheme.typography.subtitle2,
                            color = Yellow
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_leaf),
                            contentDescription = "leaf",
                            tint =
                            if (isVegan) Green
                            else {
                                if (isDarkTheme) LightGray else Gray
                            }
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Vegan",
                            style = MaterialTheme.typography.subtitle2,
                            color =
                            if (isVegan) Green
                            else {
                                if (isDarkTheme) LightGray else Gray
                            }
                        )
                    }
                }
            }
        }
    }
}