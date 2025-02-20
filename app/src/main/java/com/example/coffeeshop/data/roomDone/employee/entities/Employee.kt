package com.example.coffeeshop.data.roomDone.employee.entities

import com.example.coffeeshop.data.roomDone.position.entities.Position

data class Employee(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password:String,
    val positionId: Int?,
    val phone: String,
    val birthDate: Long,
    val hireDate: Long = UNKNOWN_CREATED_AR,
    val workScheduleId: Int?,
    val status: Int,
){
    companion object{
        const val UNKNOWN_CREATED_AR = 0L
    }
}
