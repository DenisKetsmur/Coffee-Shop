package com.example.coffeeshop.data.room.users.work_schedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shift")
data class Shift(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startTime: Long,
    val endTime: Long
)