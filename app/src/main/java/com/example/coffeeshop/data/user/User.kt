package com.example.coffeeshop.data.user

import androidx.compose.ui.graphics.painter.Painter

data class User(
    val id: Int? = 1,
    val name: String? = "name",
    val surname: String? = "surname",
    val phoneNumber: String = "234234234234",
    val email: String = "2323423@23232.com",
    val password: String = "2342342342",
    val photo: Painter? = null,
    val position: Position = Position.CLIENT,
)


