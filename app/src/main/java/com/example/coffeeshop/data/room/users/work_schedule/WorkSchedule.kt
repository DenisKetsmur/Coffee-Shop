package com.example.coffeeshop.data.room.users.work_schedule

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "work_schedule",
    foreignKeys = [
        ForeignKey(
            entity = Shift::class,
            parentColumns = ["id"],
            childColumns = ["shiftId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WorkSchedule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val shiftId: Int,
    val workSchedule: String,
    val paymentPerHour: Double
)
