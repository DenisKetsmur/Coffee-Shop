package com.example.coffeeshop.screens.cardForScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R
import com.example.coffeeshop.data.filled.productCategories
import com.example.coffeeshop.ui.theme.CoffeeAppTheme

@Composable
fun ChipGroup(
    categories: List<String>,
    selectedCategories: Set<String>,
    onCategorySelected: (Set<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                label = category,
                isSelected = selectedCategories.contains(category),
                onSelected = {
                    val newSelection = selectedCategories.toMutableSet()
                    if (!newSelection.add(category)) {
                        newSelection.remove(category)
                    }
                    onCategorySelected(newSelection)
                },
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


@Composable
fun ChipGroup(
    categories: List<String>,
    selectedCategories: Set<String>,
    onCategorySelected: (Set<String>) -> Unit,
    onIconStateChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    firstItemThreshold: Float = 470f,
    lastItemMaxThreshold: Float = firstItemThreshold - 290f,
    startPadding: Dp = 100.dp,
    endPadding: Dp = 300.dp
) {
    var firstItemX: Float? by remember { mutableStateOf(null) }
    var lastItemX: Float? by remember { mutableStateOf(null) }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.width(startPadding))
        }
        itemsIndexed(categories) { index, category ->
            FilterChip(
                label = category,
                isSelected = selectedCategories.contains(category),
                onSelected = {
                    val newSelection = selectedCategories.toMutableSet()
                    if (!newSelection.add(category)) {
                        newSelection.remove(category)
                    }
                    onCategorySelected(newSelection)
                },
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    if (index == 0) firstItemX = coordinates.positionInRoot().x
                    if (index == categories.lastIndex) lastItemX = coordinates.positionInRoot().x
                }
            )
            if (index == categories.lastIndex) {
                Spacer(modifier = Modifier.width(endPadding))
            }
            val iconRes = when {
                firstItemX != null && firstItemX!! > firstItemThreshold -> R.drawable.cat_close_mouth
                lastItemX != null && lastItemX!! < lastItemMaxThreshold -> R.drawable.cat_close_mouth
                else -> R.drawable.cat_front
            }
            onIconStateChange(iconRes)
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        ),
                modifier = modifier
            .height(40.dp)
            .clickable {
                onSelected()
                focusManager.clearFocus()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(start = 12.dp, end =  12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(label)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewChipGroup(){
    CoffeeAppTheme(darkTheme = true) {
        Surface {
            val selectedCategories by remember { mutableStateOf(setOf<String>()) }
            ChipGroup(
                categories = productCategories,
                selectedCategories = selectedCategories,
                onCategorySelected = {},
            )
        }
    }
}