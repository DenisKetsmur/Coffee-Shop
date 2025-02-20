package com.example.coffeeshop.data.roomDone.employee.entities

import com.example.coffeeshop.data.exception.EmptyFieldException
import com.example.coffeeshop.data.exception.Field
import com.example.coffeeshop.data.exception.PasswordMismatchException
import com.example.coffeeshop.data.roomDone.clients.room.entities.Position

class InvalidDateException(message: String) : Exception(message)

data class SignUpData(
    val firstName:String,
    val lastName:String,
    val position: Position,
    val birthDate: Long,
    val email: String,
    val phone:String,
    val hireDate: Long,
    val password:String,
    val workScheduleId: Int?,
    val repeatPassword:String,
){
    fun validate() {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (firstName.isBlank()) throw EmptyFieldException(Field.FirstName)
        if (lastName.isBlank()) throw EmptyFieldException(Field.LastName)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        if (phone.isBlank()) throw  EmptyFieldException(Field.Phone)
        if (password != repeatPassword) throw PasswordMismatchException()
        if (workScheduleId == null) throw EmptyFieldException(Field.WorkSchedule)

        if (birthDate <= 0) throw InvalidDateException("Некоректна дата народження")
        if (hireDate <= 0) throw InvalidDateException("Некоректна дата прийняття на роботу")
    }
}
