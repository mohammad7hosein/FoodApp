package com.smh.foodapp.presentation.ui.RecipeList

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.OutlinedTextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import com.smh.foodapp.R
import com.smh.foodapp.presentation.theme.Border
import com.smh.foodapp.presentation.theme.DarkGray
import com.smh.foodapp.presentation.theme.FoodAppTheme
import com.smh.foodapp.presentation.theme.viga

@Composable
fun RecipeListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back",
                    modifier = Modifier.size(36.dp)
                )
            }
            Text(
                text = "Foody",
                style = MaterialTheme.typography.h3,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            var searchText by remember { mutableStateOf("Search...") }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(45.dp)
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Border)
                    .padding(8.dp)
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search",
                        tint = DarkGray
                    )
                }
                BasicTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    textStyle = TextStyle(
                        color = DarkGray,
                        fontFamily = viga,
                        fontSize = 14.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {

                        }
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(12.dp)
                    .height(45.dp)
                    .width(45.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Border
                    )
                    .clickable { }

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "filter",
                    tint = DarkGray
                )
            }
        }
    }
}

