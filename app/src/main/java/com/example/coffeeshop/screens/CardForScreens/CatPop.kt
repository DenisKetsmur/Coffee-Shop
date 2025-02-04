package com.example.coffeeshop.screens.CardForScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R


@Composable
fun CatPop(
    iconState: Int,
    iconSize: Dp = 100.dp,
    paddingStart: Dp = 0.dp,
    paddingTop: Dp = 36.dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        if (iconState != R.drawable.cat_close_mouth) {
            Icon(
                painter = painterResource(R.drawable.cat_background),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.padding(start = paddingStart).size(iconSize)
            )
        }
        Box(modifier = Modifier.padding(top = paddingTop)) {
            content.invoke()
        }
        Icon(
            painter = painterResource(iconState),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.padding(start = paddingStart).size(iconSize)
        )
    }
}