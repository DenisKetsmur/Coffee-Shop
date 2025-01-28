package com.example.coffeeshop.screens.componentsMenuScreen

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.coffeeshop.R

@Composable
fun ThreeStateButton() {
    var buttonState by remember { mutableStateOf(ButtonState.BYNAME) }

    IconButton(
        onClick = {
            buttonState = when (buttonState) {
                ButtonState.BYNAME -> ButtonState.CHEAPER
                ButtonState.CHEAPER -> ButtonState.RATHER
                ButtonState.RATHER -> ButtonState.BYNAME
            }
        }
    ) {
        Icon(
            painter = when (buttonState) {
                ButtonState.CHEAPER -> painterResource(R.drawable.filterdown)
                ButtonState.RATHER -> painterResource(R.drawable.filterup)
                ButtonState.BYNAME -> painterResource(R.drawable.byname)
            },
            contentDescription = "filter",
            tint = Color.Unspecified
        )
    }
}

enum class ButtonState {
    CHEAPER, RATHER, BYNAME
}
