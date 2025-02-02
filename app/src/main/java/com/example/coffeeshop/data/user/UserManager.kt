package com.example.coffeeshop.data.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ManagerUser {
    private val users = listOf(
        User(email = "admin@gmail.com", password = "123456789", position = Position.ADMINISTRATOR),
        User(email ="manager@gmail.com", password = "123456789", position = Position.MANAGER),
    )

    var currentUser by mutableStateOf<User?>(null)

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