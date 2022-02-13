package com.smh.foodapp.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterDialogItem(
    filterType: String,
    isSelected: Boolean = false,
    onSelectedFilterTypeChanged: (String) -> Unit,
){
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (isSelected) MaterialTheme.colors.onPrimary else Color.Transparent
    ) {
        Box(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedFilterTypeChanged(filterType)
                }
            )
        ) {
            Text(
                text = filterType,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
