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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.smh.foodapp.domain.model.FilterType
import com.smh.foodapp.domain.model.getALLDietType
import com.smh.foodapp.domain.model.getAllCuisineType
import com.smh.foodapp.domain.model.getAllMealType

@Composable
fun FilterDialog(
    selectedMealType: String,
    selectedDietType: String,
    selectedCuisineType: String,
    onFilterTypeChange: (FilterType?) -> Unit,
    isDialogOpen: MutableState<Boolean>
) {
    val filterType: MutableState<FilterType?> = remember {
        mutableStateOf(null)
    }

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
                                    filterType = item.value,
                                    isSelected = selectedMealType == item.value,
                                    onSelectedFilterTypeChanged = {
                                        filterType.value?.copy(selectedMealType = it)
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
                                    filterType = item.value,
                                    isSelected = selectedDietType == item.value,
                                    onSelectedFilterTypeChanged = {
                                        filterType.value?.copy(selectedDietType = it)
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
                                    filterType = item.value,
                                    isSelected = selectedCuisineType == item.value,
                                    onSelectedFilterTypeChanged = {
                                        filterType.value?.copy(selectedCuisineType = it)
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
                            onFilterTypeChange(filterType.value)
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
