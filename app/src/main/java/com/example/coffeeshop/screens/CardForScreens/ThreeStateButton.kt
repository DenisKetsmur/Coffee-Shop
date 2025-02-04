package com.example.coffeeshop.screens.CardForScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R

@Composable
fun ThreeStateButton(
    modifier: Modifier = Modifier,
    onStateChange: (ButtonState) -> Unit = {}
) {
    var buttonState by remember { mutableStateOf(ButtonState.BYNAME) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                buttonState = when (buttonState) {
                    ButtonState.BYNAME -> ButtonState.CHEAPER
                    ButtonState.CHEAPER -> ButtonState.RATHER
                    ButtonState.RATHER -> ButtonState.BYNAME
                }
                onStateChange(buttonState)
            }
        ) {
            Icon(
                painter = painterResource(
                    when (buttonState) {
                        ButtonState.CHEAPER -> R.drawable.filterdown
                        ButtonState.RATHER -> R.drawable.filterup
                        ButtonState.BYNAME -> R.drawable.byname
                    }
                ),
                contentDescription = "filter",
                tint = Color.Unspecified
            )
        }
    }
}

enum class ButtonState {
    CHEAPER, RATHER, BYNAME
}
