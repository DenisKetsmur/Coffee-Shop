package com.example.coffeeshop.data.user

import com.example.coffeeshop.R

sealed class User(
    open val email: String,
    open val password: String,
    open val position: Position,
    open val phoneNumber: String,
) {
    data class Employee(
        override val email: String = "",
        override val password: String = "",
        override val position: Position = Position.MANAGER,
        override val phoneNumber: String = "+380345345",
        val id: Int = 1,
        val name: String = "name",
        val surname: String = "surname",
        val salary: String = "34234",
        val experience: String = "2 years",
        val startJob: String = "12/12/12",
        val photo: Int = R.mipmap.face_photo,
        val status: Boolean = true,
    ) : User(email, password, position, phoneNumber)

    data class Client(
        override val email: String = "",
        override val password: String = "",
        override val position: Position = Position.CLIENT,
        override val phoneNumber: String = "+380345345",
        val name: String = "name",
        val surname: String = "surname",
    ) : User(email, password, position, phoneNumber)
}
