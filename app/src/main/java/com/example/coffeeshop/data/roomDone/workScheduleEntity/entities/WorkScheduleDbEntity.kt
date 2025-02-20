package com.example.coffeeshop.data.roomDone.workScheduleEntity.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.coffeeshop.data.roomDone.employee.room.entities.EmployeeDbEntity

@Entity(
    tableName = "work_schedule",
    foreignKeys = [
        ForeignKey(
            entity = EmployeeDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["employees_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("employees_id")]
)
data class WorkScheduleDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "start_time") val startTime: Long,
    @ColumnInfo(name = "end_time") val endTime: Long,
    @ColumnInfo(name = "work_schedule") val workSchedule: String,
    @ColumnInfo(name = "payment_per_hour") val paymentPerHour: Double,
    @ColumnInfo(name = "employees_id") val employeesId: Int
)

