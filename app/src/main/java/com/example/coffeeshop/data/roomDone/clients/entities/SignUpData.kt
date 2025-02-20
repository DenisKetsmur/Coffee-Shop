package com.example.coffeeshop.data.roomDone.clients.entities

import com.example.coffeeshop.data.exception.EmptyFieldException
import com.example.coffeeshop.data.exception.Field
import com.example.coffeeshop.data.exception.PasswordMismatchException

data class SignUpData(
    val firstName:String,
    val lastName:String,
    val email: String,
    val phone:String,
    val password:String,
    val repeatPassword:String,
){
    fun validate() {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (firstName.isBlank()) throw EmptyFieldException(Field.FirstName)
        if (lastName.isBlank()) throw EmptyFieldException(Field.LastName)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        if (phone.isBlank()) throw  EmptyFieldException(Field.Phone)
        if (password != repeatPassword) throw PasswordMismatchException()
    }
}