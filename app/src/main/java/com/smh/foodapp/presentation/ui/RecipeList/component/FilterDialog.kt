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
    onSelectedMealTypeChanged: (String) -> Unit,
    onSelectedDietTypeChanged: (String) -> Unit,
//    selectedCuisineType: CuisineType,
//    onSelectedCuisineTypeChanged: (String) -> Unit,
    onFilterTypeChanged: () -> Unit,
    isDialogOpen: MutableState<Boolean>
) {

    if (isDialogOpen.value) {
        Dialog(
            onDismissRequest = { isDialogOpen.value = false }
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
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
                        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
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
//                        LazyColumn {
//                            item {
//                                Text(
//                                    text = "Cuisine",
//                                    style = MaterialTheme.typography.body1,
//                                    color = MaterialTheme.colors.onBackground,
//                                    textAlign = TextAlign.Center,
//                                    modifier = Modifier.padding(bottom = 16.dp)
//                                )
//                            }
//                            items(getAllCuisineType()) { item ->
//                                FilterDialogItem(
//                                    filterType = item.text,
//                                    isSelected = selectedCuisineType == item,
//                                    onSelectedFilterTypeChanged = {
//                                        onSelectedCuisineTypeChanged(it)
//                                    }
//                                )
//                            }
//                        }
                    }
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
