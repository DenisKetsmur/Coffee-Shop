package com.example.coffeeshop.screens.componentsMenuScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun ChipGroup(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.then(
            Modifier.fillMaxWidth()
            .padding(8.dp)),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                label = category,
                isSelected = selectedCategory == category,
                onSelected = { onCategorySelected.invoke(category) }
            )
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = {
            onSelected.invoke()
            focusManager.clearFocus()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.height(40.dp),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(label)
    }
}