package com.example.coffeeshop.data.user

import com.example.coffeeshop.R
import com.example.coffeeshop.data.productAndGoods.Goods
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
        val age: Int? = null,
        val startJob: Long = 0,
        val photo: Int = R.mipmap.face_photo,
        val isActive: Boolean = true,
        var workSchedule: WorkSchedule = WorkSchedule.NONE,
    ) : User(id, name, surname, email, password, position, phoneNumber)
    data class Client(
        override val id: Int = generateId(),
        override val name: String = "",
        override val surname: String = "",
        override val email: String = "",
        override val password: String = "",
        override val position: Position = Position.CLIENT,
        override val phoneNumber: String = "+380345345",

        val orders:List<Order<Goods>> = listOf()
    ) : User(id, name, surname, email, password, position, phoneNumber)
}


data class WorkSchedule(
    val shift: Shift,
    val workSchedule: String,
    val hourlyRate: Double
) {
    companion object {
        val NONE: WorkSchedule = WorkSchedule(
            shift = Shift.NONE,
            workSchedule = "00",
            hourlyRate = 0.0
        )
    }
}




enum class Shift(val time:String) {
    NONE("NONE"),
    FIRST("8:00-16:00"),
    SECOND("16:00-24:00")
}



