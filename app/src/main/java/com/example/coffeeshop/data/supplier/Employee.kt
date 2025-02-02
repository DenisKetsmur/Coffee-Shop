package com.example.coffeeshop.data.supplier

import androidx.compose.ui.graphics.painter.Painter
import com.example.coffeeshop.data.user.Position

data class Employee(
    val id:Int = 1,
    val name: String = "name",
    val surname:String = "surname",
    val phoneNumber: String = "+380345345",
    val email:String = "2323423@23232.com",
    val position: Position = Position.MANAGER,
    val salary:String = "34234",
    val experience:String = "2 years",
    val startJob:String = "12/12/12",
    val photo: Painter,
    val status:String = "активний",
)
