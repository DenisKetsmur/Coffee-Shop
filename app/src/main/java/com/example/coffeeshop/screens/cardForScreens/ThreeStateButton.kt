package com.example.coffeeshop.screens.cardForScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R

@Composable
fun ThreeStateButton(
    modifier: Modifier = Modifier
) {
    var buttonState by remember { mutableStateOf(ButtonState.NEUTRAL) }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                buttonState = when (buttonState) {
                    ButtonState.NEUTRAL -> ButtonState.CHEAPER
                    ButtonState.CHEAPER -> ButtonState.RATHER
                    ButtonState.RATHER -> ButtonState.NEUTRAL
                }
            },
        ) {
            Icon(
                painter = painterResource(
                    when (buttonState) {
                        ButtonState.CHEAPER -> R.drawable.filterdown
                        ButtonState.RATHER -> R.drawable.filterup
                        ButtonState.NEUTRAL -> R.drawable.neutral
                    }
                ),
                contentDescription = "filter",
                tint = Color.Unspecified
            )
        }
    }
}

enum class ButtonState {
    CHEAPER, RATHER, NEUTRAL
}
