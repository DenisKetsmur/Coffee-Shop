package com.example.coffeeshop.data.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ManagerUser {
    val users = listOf(
        User.Employee(id = 1,email = "admin@gmail.com", password = "123456789", position = Position.ADMINISTRATOR),
        User.Employee(id = 2,email = "manager@gmail.com", password = "123456789", position = Position.MANAGER),
        User.Client(id = 3, email = "client@gmail.com", password = "123456789", position = Position.CLIENT)
    )

    var currentUser by mutableStateOf<User?>(users[1])
    var currentUserId by mutableStateOf(currentUser?.id?: 2)


    fun login(email: String, password: String): Boolean {
        val user = users.find { it.email == email && it.password == password }
        return if (user is User.Employee || user is User.Client) {
            currentUser = user
            true
        } else {
            false
        }
    }

    fun logout() {
        currentUser = null
    }

    fun isLogin(): Boolean = currentUser != null
    fun isAdmin(): Boolean = (currentUser as? User.Employee)?.position == Position.ADMINISTRATOR
    fun isManager(): Boolean = (currentUser as? User.Employee)?.position == Position.MANAGER
    fun isClient(): Boolean = currentUser is User.Client
}