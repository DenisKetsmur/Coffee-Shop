package com.example.coffeeshop.data.user

import com.example.coffeeshop.R
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.data.supplier.Order

sealed class User(
    open val id:Int,
    open val name: String,
    open val surname: String,
    open val email: String,
    open val password: String,
    open val position: Position,
    open val phoneNumber: String,

) {
    companion object {
        private var idCounter = 0
        private fun generateId(): Int {
            return ++idCounter
        }
    }
    data class Employee(
        override val id: Int = generateId(),
        override val name: String = "",
        override val surname: String = "",
        override val email: String = "",
        override val password: String = "",
        override val position: Position = Position.MANAGER,
        override val phoneNumber: String = "+380345345",

        val salary: Double = 0.0,
        val startJob: Long = 0,
        val photo: Int = R.mipmap.face_photo,
        val isActive: Boolean = true,
    ) : User(id, name, surname, email, password, position, phoneNumber)
    data class Client(
        override val id: Int = generateId(),
        override val name: String = "",
        override val surname: String = "",
        override val email: String = "",
        override val password: String = "",
        override val position: Position = Position.CLIENT,
        override val phoneNumber: String = "+380345345",

        val orders:List<Order<Product>> = listOf()
    ) : User(id, name, surname, email, password, position, phoneNumber)
}



