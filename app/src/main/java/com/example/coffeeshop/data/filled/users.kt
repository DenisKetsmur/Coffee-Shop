package com.example.coffeeshop.data.filled

import com.example.coffeeshop.data.user.Position
import com.example.coffeeshop.data.user.Shift
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.data.user.WorkSchedule
import java.util.Calendar
import kotlin.random.Random

fun getRandomDate(): Long {
    val calendar = Calendar.getInstance()
    val now = calendar.timeInMillis
    calendar.add(Calendar.YEAR, -2) // Відняти 2 роки
    val past = calendar.timeInMillis

    return Random.nextLong(past, now) // Генеруємо випадковий час у мілісекундах
}

val sampleEmployee = User.Employee(
    email = "manager@gmail.com",
    password = "123456789",
    position = Position.MANAGER,
    phoneNumber = "+380345345",
    name = "Борис",
    surname = "Ангелінів",
    workSchedule = WorkSchedule(
        shift = Shift.FIRST,
        workSchedule = "33",
        hourlyRate = 10.0,
    ),
    age = 23,
    startJob = getRandomDate(),
)

val client = User.Client(
    name = "Олег",
    surname = "Сидоров",
    email = "oleg.sid@example.com",
    password = "securepassword",
    phoneNumber = "+380987654321",

)


