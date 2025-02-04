package com.example.coffeeshop.screens.CardForScreens

import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R


@Composable
fun CatPop(
    modifier: Modifier = Modifier,
    content:@Composable ()->Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){

        Icon(
            painter = painterResource(R.drawable.cat_background),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(100.dp)
        )
        Box(
            modifier.padding(top = 38.dp)
        ){
            content.invoke()
        }
        Icon(
            painter = painterResource(R.drawable.cat_front),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(100.dp)
        )
    }


}

@Preview
@Composable
fun PreviewCatPop(){
    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки")
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    CatPop {
        ChipGroup(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                selectedCategory = category
            }
        )
    }
}