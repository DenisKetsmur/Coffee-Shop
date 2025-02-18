package com.example.coffeeshop.data.formatting

import java.text.DecimalFormat

fun formatWithoutZero(value: Double): String {
    val formatter = DecimalFormat("#.##")
    return formatter.format(value)
}

fun formatWithoutZero(value: Float): String {
    val formatter = DecimalFormat("#.##")
    return formatter.format(value)
}