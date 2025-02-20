package com.example.coffeeshop.data.filled

import java.util.Calendar
import kotlin.random.Random

fun getRandomDate(): Long {
    val calendar = Calendar.getInstance()
    val now = calendar.timeInMillis
    calendar.add(Calendar.YEAR, -2) // Відняти 2 роки
    val past = calendar.timeInMillis

    return Random.nextLong(past, now) // Генеруємо випадковий час у мілісекундах
}



