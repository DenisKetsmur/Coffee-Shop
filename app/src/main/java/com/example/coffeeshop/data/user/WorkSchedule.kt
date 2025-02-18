package com.example.coffeeshop.data.user

data class WorkSchedule(
    val shift: Shift,
    val workSchedule: String,
    val hourlyRate: Double
) {
    companion object {
        val NONE: WorkSchedule = WorkSchedule(
            shift = Shift.NONE,
            workSchedule = "00",
            hourlyRate = 0.0
        )
    }
}

enum class Shift(val time:String) {
    NONE("NONE"),
    FIRST("8:00-16:00"),
    SECOND("16:00-24:00")
}