package com.example.coffeeshop.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ManagerUser {
    private val users = listOf(
        User("admin@gmail.com", "admin1", Position.ADMINISTRATOR),
        User("manager@gmail.com", "manager", Position.MANAGER)
    )

    var currentUser by mutableStateOf<User?>(User("admin@gmail.com", "admin1", Position.ADMINISTRATOR))

    fun login(email: String, password: String): Boolean {
        val user = users.find { it.login == email && it.password == password }
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