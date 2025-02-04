package com.example.coffeeshop.data.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ManagerUser {
    private val users = listOf(
        User(email = "admin@gmail.com", password = "123456789", position = Position.ADMINISTRATOR),
        User(email ="manager@gmail.com", password = "123456789", position = Position.MANAGER),
    )

    var currentUser by mutableStateOf<User?>(User(email = "admin@gmail.com", password = "123456789", position = Position.ADMINISTRATOR))

    fun login(email: String, password: String): Boolean {
        val user = users.find { it.email == email && it.password == password }
        return if (user != null) {
            currentUser = user
            true
        } else {
            false
        }
    }

    fun logout() {
        currentUser = null
    }

    fun isAdmin(): Boolean = currentUser?.position == Position.ADMINISTRATOR
    fun isManager(): Boolean = currentUser?.position == Position.MANAGER
}


/*object ManagerUser {
    private val users = listOf(
        User.Employee(email = "admin@gmail.com", password = "123456789", position = Position.ADMINISTRATOR),
        User.Employee(email = "manager@gmail.com", password = "123456789", position = Position.MANAGER),
        User.Client(email = "client@gmail.com", password = "123456789", discount = 10.0)
    )

    var currentUser by mutableStateOf<User?>(null)

    fun login(email: String, password: String): Boolean {
        val user = users.find { it.email == email && it.password == password }
        return if (user is User.Employee || user is User.Client) { // Перевірка типу
            currentUser = user
            true
        } else {
            false
        }
    }

    fun logout() {
        currentUser = null
    }

    fun isAdmin(): Boolean = (currentUser as? User.Employee)?.position == Position.ADMINISTRATOR
    fun isManager(): Boolean = (currentUser as? User.Employee)?.position == Position.MANAGER
    fun isClient(): Boolean = currentUser is User.Client
}*/