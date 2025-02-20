package com.example.coffeeshop.data.roomDone.employee.room.entities

import androidx.room.ColumnInfo

data class EmployeeSignInTuple(
    val id:Long,
    val password:String,
)

data class EmployeeUpdateTuple(
    val id:Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val phone: String,
    val password:String,
    val birthDate: Long,
    val positionId: Int?,
    val workScheduleId: Int?,
    val status: Int
)

data class EmployeeUpdateHimTuple(
    val id:Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val phone: String,
    val password:String,
    val birthDate: Long,
)
