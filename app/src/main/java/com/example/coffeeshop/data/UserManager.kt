package com.example.coffeeshop.data

object ManagerUser {
    private val users = listOf(
        User("admin@gmail.com", "admin11", Position.ADMINISTRATOR),
        User("manager@gmail.com", "manager", Position.MANAGER)
    )

    var currentUser: User? = null
        private set

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