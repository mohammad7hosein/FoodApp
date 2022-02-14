package com.smh.foodapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.smh.foodapp.domain.model.*

@Composable
fun FilterDialog(
    selectedMealType: MealType,
    selectedDietType: DietType,
    selectedCuisineType: CuisineType,
    onSelectedMealTypeChanged: (String) -> Unit,
    onSelectedDietTypeChanged: (String) -> Unit,
    onSelectedCuisineTypeChanged: (String) -> Unit,
    onFilterTypeChanged: () -> Unit,
    isDialogOpen: MutableState<Boolean>
) {

    if (isDialogOpen.value) {
        Dialog(
            onDismissRequest = { isDialogOpen.value = false }
        ) {
            Surface(
                modifier = Modifier
                    .width(600.dp)
                    .height(500.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LazyColumn {
                            item {
                                Text(
                                    text = "MealType",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                            }
                            items(getAllMealType()) { item ->
                                FilterDialogItem(
                                    filterType = item.text,
                                    isSelected = selectedMealType == item,
                                    onSelectedFilterTypeChanged = {
                                        onSelectedMealTypeChanged(it)
                                    }
                                )
                            }
                        }
                        LazyColumn {
                            item {
                                Text(
                                    text = "DietType",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                            }
                            items(getALLDietType()) { item ->
                                FilterDialogItem(
                                    filterType = item.text,
                                    isSelected = selectedDietType == item,
                                    onSelectedFilterTypeChanged = {
                                        onSelectedDietTypeChanged(it)
                                    }
                                )
                            }
                        }
                        LazyColumn {
                            item {
                                Text(
                                    text = "Cuisine",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                            }
                            items(getAllCuisineType()) { item ->
                                FilterDialogItem(
                                    filterType = item.text,
                                    isSelected = selectedCuisineType == item,
                                    onSelectedFilterTypeChanged = {
                                        onSelectedCuisineTypeChanged(it)
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colors.onPrimary
                        ),
                        onClick = {
                            isDialogOpen.value = false
                            onFilterTypeChanged()
                        }) {
                        Text(
                            text = "Apply",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }

            }
        }
    }
}
